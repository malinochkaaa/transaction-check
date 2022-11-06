package dev.zio.quickstart.transaction

import zhttp.http.*
import zio.*
import zio.json.*
import zio.stream.ZStream
import scala.io.Source

case class Transaction(src: String,
                       dst: String,
                       amount: Int)

object TransactionApp:
  implicit val encoder: JsonEncoder[Transaction] = DeriveJsonEncoder.gen[Transaction]
  implicit val decoder: JsonDecoder[Transaction] = DeriveJsonDecoder.gen[Transaction]
  implicit val codec: JsonCodec[Transaction] = DeriveJsonCodec.gen[Transaction]

  def apply(): Http[Any, Throwable, Request, Response] =
    Http.collectZIO[Request] {
      // POST /users -d '{"name": "John", "age": 35}'
      case req@(Method.POST -> !! / "transaction-check") =>
        val blacklist = Source.fromResource("bigfile.txt").getLines()
        for {
          u <- req.bodyAsString.map(_.fromJson[Transaction])
          r <- u match
            case Left(e) =>
              ZIO.debug(s"Failed to parse the input: $e").as(
                Response.text(e).setStatus(Status.BadRequest)
              )
            case Right(u) =>
              for (line <- blacklist) {
                if (line.equals(u.src) || line.equals(u.dst))
                  Response.text("Cancel")
                else
                  Response.text("Succeed")
              }
        } yield r
    }
