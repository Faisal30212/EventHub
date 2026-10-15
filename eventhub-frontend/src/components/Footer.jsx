import "./Footer.css";

function Footer() {
  return (
    <footer className="footer">
      <div className="container">
        <div className="row g-4">
          {/* Brand */}

          <div className="col-lg-4 col-md-6">
            <h2 className="footer-logo">EventHub</h2>

            <p className="footer-description">
              Your trusted event management platform for creating memorable
              weddings, birthdays, conferences, concerts, sports events and
              college festivals.
            </p>
          </div>

          {/* Quick Links */}

          <div className="col-lg-2 col-md-6">
            <h5>Quick Links</h5>

            <ul>
              <li>
                <a href="/">Home</a>
              </li>

              <li>
                <a href="/wedding">Wedding</a>
              </li>

              <li>
                <a href="/birthday">Birthday</a>
              </li>

              <li>
                <a href="/conference">Conference</a>
              </li>
            </ul>
          </div>

          {/* Events */}

          <div className="col-lg-3 col-md-6">
            <h5>Event Categories</h5>

            <ul>
              <li>
                <a href="/concert">Concert</a>
              </li>

              <li>
                <a href="/sports">Sports</a>
              </li>

              <li>
                <a href="/collegefest">College Fest</a>
              </li>

              <li>
                <a href="/reviews">Reviews</a>
              </li>
            </ul>
          </div>

          {/* Contact */}

          <div className="col-lg-3 col-md-6">
            <h5>Contact Us</h5>

            <p className="footer-contact">📍 Karnataka, India</p>

            <p className="footer-contact">📞 +91 98765 43210</p>

            <p className="footer-contact">✉️ eventhub@gmail.com</p>
          </div>
        </div>

        {/* Bottom */}

        <div className="footer-bottom">
          <p>© 2026 EventHub. All rights reserved.</p>

          <p>Made with ❤️ for unforgettable events.</p>
        </div>
      </div>
    </footer>
  );
}

export default Footer;
