package dev.zio.quickstart.transaction

import zhttp.http.*
import zio.*
import zio.stream.ZStream
import scala.io.Source

object TransactionApp:
  def apply(): Http[Any, Nothing, Request, Response] =
    Http.collect[Request] {
      case Method.GET -> !! / "transaction-check" =>
        val scalaFileContents = Source.fromFile(file).getLines()

    }
