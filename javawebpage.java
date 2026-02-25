import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class MyWebServer {
  public static void main(String[] args) throws IOException {
    int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
    HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
    server.createContext("/", new MyHandler());
    server.setExecutor(null);
    System.out.println("Server chalu: http://localhost:8080");
    server.start();
  }

  static class MyHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
      String path = t.getRequestURI().getPath();
      String title = "Home Page";
      String desc = "Welcome to our Java World!";
      String bgImage = "https://images.unsplash.com/photo-1451187580459-43490279c0fa?q=80&w=1920";
      String mainContent = "";

      // Same Routing Logic as before
      if (path.equals("/services")) {
        title = "Our Services"; desc = "High-end Java & Ubuntu Solutions.";
        bgImage = "https://images.unsplash.com/photo-1518770660439-4636190af475?q=80&w=1920";
      } else if (path.equals("/portfolio")) {
        title = "My Portfolio"; bgImage = "https://images.unsplash.com/photo-1507238691740-187a5b1d37b8?q=80&w=1920";
        mainContent = "<div class='card-container'>" +
          "   <div class='card'><img src='https://images.unsplash.com/photo-1555066931-4365d14bab8c?w=400' /><h3>Web App</h3><p>Java Backend Project.</p></div>" +
          "   <div class='card'><img src='https://images.unsplash.com/photo-1460925895917-afdab827c52f?w=400' /><h3>Cloud Data</h3><p>Secure Cloud Storage.</p></div>" +
          "   <div class='card'><img src='https://images.unsplash.com/photo-1558494949-ef010cbdcc51?w=400' /><h3>Secure Hosting</h3><p>Fast and Safe Servers.</p></div>" +
        "</div>";
      } else if (path.equals("/about")) {
        title = "About Us"; desc = "We are Ubuntu Terminal Experts.";
        bgImage = "https://images.unsplash.com/photo-1522071820081-009f0129c71c?q=80&w=1920";
      } else if (path.equals("/contact")) {
        title = "Contact Us"; bgImage = "https://images.unsplash.com/photo-1423666639041-f56000c27a9a?q=80&w=1920";
        mainContent = "<div class='glass-card' style='width: 400px;'><h2>Get In Touch</h2><br>" +
          "   <form style='display:flex; flex-direction:column; gap:10px;'>" +
          "  <input type='text' placeholder='Name' style='padding:10px; border-radius:5px; border:none;'>" +
          "  <textarea placeholder='Message' rows='3' style='padding:10px; border-radius:5px; border:none;'></textarea>" +
          "  <button type='button' style='padding:10px; background:#00d4ff; border:none; border-radius:5px; font-weight:bold;'>Send</button>" +
          "   </form></div>";
      }

      if (mainContent.isEmpty()) { mainContent = "<div class='glass-card'><h1>" + title + "</h1><p>" + desc + "</p></div>"; }

      String response = "<html><head>" +
        "<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css'>" +
        "<style>" +
        "   * { margin: 0; padding: 0; box-sizing: border-box; }" +
        "   body { font-family: 'Segoe UI', sans-serif; display: flex; flex-direction: column; min-height: 100vh; }" +
        "   header { background: rgba(0,0,0,0); color: white; padding: 25px 0; position: fixed; width: 100%; top: 0; z-index: 100; display: flex; justify-content: space-around; align-items: center; }" +
        "   nav a { color: white; text-decoration: none; margin: 0 15px; font-weight: bold; text-transform: uppercase; font-size: 13px; }" +
        "   .hero { min-height: 100vh; width: 100%; background: linear-gradient(rgba(0,0,0,0.6), rgba(0,0,0,0.6)), url('" + bgImage + "'); background-size: cover; background-position: center; display: flex; align-items: center; justify-content: center; color: white; padding: 100px 20px; }" +
        "   .glass-card { background: rgba(255, 255, 255, 0.1); backdrop-filter: blur(15px); padding: 40px; border-radius: 20px; border: 1px solid rgba(255, 255, 255, 0.2); text-align: center; }" +
        "   .card-container { display: flex; gap: 20px; flex-wrap: wrap; justify-content: center; }" +
        "   .card { background: rgba(0,0,0,0.7); backdrop-filter: blur(10px); border: 1px solid rgba(255,255,255,0.1); padding: 20px; border-radius: 15px; width: 280px; text-align: center; }" +
        "   .card img { width: 100%; height: 160px; object-fit: cover; border-radius: 10px; margin-bottom: 15px; }" +
        "   /* --- New Professional Footer --- */" +
        "   footer { background: #111; color: white; padding: 60px 10% 20px 10%; border-top: 3px solid #00d4ff; }" +
        "   .footer-row { display: flex; justify-content: space-between; flex-wrap: wrap; gap: 40px; margin-bottom: 40px; }" +
        "   .footer-col { flex: 1; min-width: 200px; }" +
        "   .footer-col h3 { color: #00d4ff; margin-bottom: 20px; font-size: 18px; text-transform: uppercase; }" +
        "   .footer-col p { color: #bbb; line-height: 1.6; font-size: 14px; }" +
        "   .footer-col ul { list-style: none; }" +
        "   .footer-col ul li { margin-bottom: 10px; }" +
        "   .footer-col ul li a { color: #bbb; text-decoration: none; transition: 0.3s; font-size: 14px; }" +
        "   .footer-col ul li a:hover { color: white; padding-left: 5px; }" +
        "   .social-links a { color: white; font-size: 20px; margin-right: 15px; transition: 0.3s; }" +
        "   .social-links a:hover { color: #00d4ff; }" +
        "   .footer-bottom { border-top: 1px solid #333; padding-top: 20px; text-align: center; color: #777; font-size: 13px; }" +
        "</style></head><body>" +
        "   <header><div style='font-size: 24px; font-weight: bold;'>JAVA <span style='color:#00d4ff'>PRO</span></div>" +
        "  <nav><a href='/'>Home</a><a href='/services'>Services</a><a href='/portfolio'>Portfolio</a><a href='/about'>About</a><a href='/contact'>Contact</a></nav>" +
        "   </header>" +
        "   <section class='hero'>" + mainContent + "</section>" +
        "   <footer>" +
        "  <div class='footer-row'>" +
        "     <div class='footer-col'>" +
        "    <h3>Java Master</h3>" +
        "    <p>Creating professional web solutions using Java and Ubuntu. We focus on high-speed and secure applications.</p>" +
        "     </div>" +
        "     <div class='footer-col'>" +
        "    <h3>Quick Links</h3>" +
        "    <ul>" +
        "       <li><a href='/'>Home</a></li>" +
        "       <li><a href='/services'>Services</a></li>" +
        "       <li><a href='/portfolio'>Portfolio</a></li>" +
        "       <li><a href='/contact'>Contact</a></li>" +
        "    </ul>" +
        "     </div>" +
        "     <div class='footer-col'>" +
        "    <h3>Contact Us</h3>" +
        "    <p><i class='fas fa-map-marker-alt'></i> Ahmedabad, Gujarat, India</p>" +
        "    <p><i class='fas fa-phone'></i> +91 98765 43210</p>" +
        "    <p><i class='fas fa-envelope'></i> hello@javapro.com</p>" +
        "     </div>" +
        "     <div class='footer-col'>" +
        "    <h3>Follow Me</h3>" +
        "    <div class='social-links'>" +
        "       <a href='#'><i class='fab fa-facebook-f'></i></a>" +
        "       <a href='#'><i class='fab fa-twitter'></i></a>" +
        "       <a href='#'><i class='fab fa-instagram'></i></a>" +
        "       <a href='#'><i class='fab fa-linkedin-in'></i></a>" +
        "    </div>" +
        "     </div>" +
        "  </div>" +
        "  <div class='footer-bottom'>&copy; 2026 Java Pro Web Dev | Designed on Ubuntu Terminal</div>" +
        "   </footer>" +
        "</body></html>";

      t.sendResponseHeaders(200, response.length());
      OutputStream os = t.getResponseBody();
      os.write(response.getBytes());
      os.close();
    }
  }
}
