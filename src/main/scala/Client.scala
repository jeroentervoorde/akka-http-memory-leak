import java.io.OutputStream
import java.net.Socket

object Client extends App {
  val postDataBody: String = {
    (for (_ <- 0 until 1) yield "blablabla").mkString
  }

  for {
    _ <- 0 until 4242
  } {
    val socket: Socket = new Socket("127.0.0.1", 9000)

    for {
      _ <- 0 until 10
    } {
      try {
        sendPost2(socket)
      } catch {
        case ex: Throwable =>
          //ex.printStackTrace()
      }
    }

    socket.close()
  }

  def sendPost2(echoSocket: Socket) {
    // POST example url
    val sb: StringBuilder = new StringBuilder()
    sb.append("POST /ping HTTP/1.1\r\n")
    sb.append("Host: 127.0.0.1\r\n")
    sb.append("Content-Type: text/plain\r\n")
    sb.append("Content-Length: " + postDataBody.getBytes("UTF-8").length + "\r\n")
    sb.append("\r\n")
    sb.append(postDataBody)

    val out: OutputStream = echoSocket.getOutputStream
    out.write(sb.toString.getBytes("UTF-8"))
    out.flush()
  }
}