import java.io.OutputStream
import java.net.Socket

object Client extends App {
  val postDataBody: String = {
    (for (_ <- 0 until 1000) yield "blablabla").mkString
  }

  for {
    _ <- 0 until 1000
  } {
    val socket: Socket = new Socket("127.0.0.1", 9000)

    for {
      _ <- 0 until 100
    } {
      try {
        sendPost2(socket)
      } catch {
        case ex: Throwable =>
          //ex.printStackTrace()
      }
    }

    //socket.close()
  }

  val USER_AGENT: String = "Mozilla/5.0 (Windows NT 6.3; rv:36.0) Gecko/20100101 Firefox/36.04"
  // GET action url, query string appended to the url as ?stype=models

  def sendPost2(echoSocket: Socket) {
    // POST example url
    val sb: StringBuilder = new StringBuilder()
    sb.append("POST /memory/leak HTTP/1.1\r\n")
    sb.append("Host: 127.0.0.1\r\n")
    sb.append("User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; rv:18.0) Gecko/20100101 Firefox/18.0\r\n")
    sb.append("Connection: keep-alive\r\n")
    sb.append("Content-Encoding: chunked")
    sb.append("Content-Type: application/xml\r\n")
    sb.append("Content-Length: " + postDataBody.getBytes("UTF-8").length + "\r\n")
    sb.append("Authorization: Basic Lalala\r\n")
    sb.append("\r\n")
    sb.append(postDataBody)
    //Socket socket = new Socket("127.0.0.1", 9016);
    val out: OutputStream = echoSocket.getOutputStream
    out.write(sb.toString.getBytes("UTF-8"))
    out.flush()
    //socket.close();
  }
}