import { useState } from "react";
import { Link } from "react-router-dom";
import "./Navbar.css";

function Navbar() {
  const [activePopup, setActivePopup] = useState(null);

  // Open the selected information popup
  const openPopup = (type) => {
    setActivePopup(type);
  };

  // Close the popup
  const closePopup = () => {
    setActivePopup(null);
  };

  return (
    <>
      {/* =====================================================
          NAVBAR
          ===================================================== */}

      <nav className="navbar navbar-expand-lg custom-navbar">
        <div className="container">
          {/* Logo */}

          <Link className="navbar-brand logo" to="/">
            EventHub
          </Link>

          {/* Mobile Menu Button */}

          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarContent"
            aria-controls="navbarContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>

          {/* Navigation */}

          <div className="collapse navbar-collapse" id="navbarContent">
            <ul className="navbar-nav ms-auto align-items-center">
              {/* Venues */}

              <li className="nav-item">
                <Link className="nav-link" to="/venues">
                  Venues
                </Link>
              </li>

              {/* Reviews */}

              <li className="nav-item">
                <Link className="nav-link" to="/reviews">
                  Reviews
                </Link>
              </li>

              {/* My Events */}

              <li className="nav-item">
                <Link className="nav-link" to="/my-bookings">
                  My Events
                </Link>
              </li>

              {/* Previous Events */}

              <li className="nav-item">
                <Link className="nav-link" to="/previous-events">
                  Previous Events
                </Link>
              </li>

              {/* Services */}

              <li className="nav-item">
                <button
                  type="button"
                  className="nav-link nav-info-button"
                  onClick={() => openPopup("services")}
                >
                  Services
                </button>
              </li>

              {/* About */}

              <li className="nav-item">
                <button
                  type="button"
                  className="nav-link nav-info-button"
                  onClick={() => openPopup("about")}
                >
                  About
                </button>
              </li>

              {/* Contact */}

              <li className="nav-item">
                <button
                  type="button"
                  className="nav-link nav-info-button"
                  onClick={() => openPopup("contact")}
                >
                  Contact
                </button>
              </li>

              {/* Login */}

              <li className="nav-item ms-3">
                <Link className="btn login-btn" to="/login">
                  Login
                </Link>
              </li>

              {/* Register */}

              <li className="nav-item ms-2">
                <Link className="btn register-btn" to="/register">
                  Register
                </Link>
              </li>
            </ul>
          </div>
        </div>
      </nav>

      {/* =====================================================
          INFORMATION POPUP
          ===================================================== */}

      {activePopup && (
        <div className="info-popup-overlay" onClick={closePopup}>
          <div className="info-popup" onClick={(e) => e.stopPropagation()}>
            {/* Close Button */}

            <button
              type="button"
              className="popup-close"
              onClick={closePopup}
              aria-label="Close"
            >
              ×
            </button>

            {/* =================================================
                SERVICES
                ================================================= */}

            {activePopup === "services" && (
              <div className="popup-content">
                <div className="popup-icon">⚙️</div>

                <span className="popup-label">EVENTHUB SERVICES</span>

                <h2>Our Services</h2>

                <p className="popup-intro">
                  EventHub provides complete event management services so you
                  can enjoy your special occasion without worrying about the
                  arrangements.
                </p>

                <div className="popup-service-grid">
                  <div className="popup-service-card">
                    <div className="service-card-icon">💍</div>

                    <h3>Wedding Planning</h3>

                    <p>
                      Complete wedding planning including venue, decoration,
                      catering and event coordination.
                    </p>
                  </div>

                  <div className="popup-service-card">
                    <div className="service-card-icon">🎂</div>

                    <h3>Birthday Events</h3>

                    <p>
                      Theme decoration, catering and complete birthday event
                      management.
                    </p>
                  </div>

                  <div className="popup-service-card">
                    <div className="service-card-icon">💼</div>

                    <h3>Corporate Events</h3>

                    <p>
                      Professional conferences, seminars and corporate event
                      arrangements.
                    </p>
                  </div>

                  <div className="popup-service-card">
                    <div className="service-card-icon">🎵</div>

                    <h3>Concerts & Entertainment</h3>

                    <p>
                      Venue, stage, sound system and complete entertainment
                      event coordination.
                    </p>
                  </div>

                  <div className="popup-service-card">
                    <div className="service-card-icon">🏆</div>

                    <h3>Sports Events</h3>

                    <p>
                      Sports event planning, venue arrangements and event
                      coordination.
                    </p>
                  </div>

                  <div className="popup-service-card">
                    <div className="service-card-icon">🎓</div>

                    <h3>College Fests</h3>

                    <p>
                      Cultural and technical fest planning with complete event
                      management.
                    </p>
                  </div>
                </div>
              </div>
            )}

            {/* =================================================
                ABOUT
                ================================================= */}

            {activePopup === "about" && (
              <div className="popup-content">
                <div className="popup-icon">ℹ️</div>

                <span className="popup-label">ABOUT EVENTHUB</span>

                <h2>About EventHub</h2>

                <p className="popup-intro">
                  EventHub is an event management platform designed to make
                  event planning simple, organized and stress-free.
                </p>

                <div className="popup-about-grid">
                  <div className="popup-about-card">
                    <div className="about-card-icon">🎯</div>

                    <h3>Our Mission</h3>

                    <p>
                      Our mission is to help customers organize memorable events
                      by providing professional planning and management
                      services.
                    </p>
                  </div>

                  <div className="popup-about-card">
                    <div className="about-card-icon">👨‍💼</div>

                    <h3>Event Manager Support</h3>

                    <p>
                      After submitting an event request, our Event Manager
                      reviews the requirements and works with the customer to
                      prepare the final event plan and quotation.
                    </p>
                  </div>

                  <div className="popup-about-card">
                    <div className="about-card-icon">🏢</div>

                    <h3>Complete Event Management</h3>

                    <p>
                      From venue selection to decoration, catering and other
                      requirements, EventHub helps coordinate the complete
                      event.
                    </p>
                  </div>

                  <div className="popup-about-card">
                    <div className="about-card-icon">🤝</div>

                    <h3>Customer First</h3>

                    <p>
                      We focus on understanding every customer's requirements
                      and creating an event plan according to their needs.
                    </p>
                  </div>
                </div>
              </div>
            )}

            {/* =================================================
                CONTACT
                ================================================= */}

            {activePopup === "contact" && (
              <div className="popup-content">
                <div className="popup-icon">📞</div>

                <span className="popup-label">GET IN TOUCH</span>

                <h2>Contact EventHub</h2>

                <p className="popup-intro">
                  Have questions about your event? Contact our EventHub team and
                  we will be happy to help.
                </p>

                <div className="popup-contact-grid">
                  <div className="popup-contact-card">
                    <div className="contact-card-icon">📞</div>

                    <div>
                      <h3>Phone</h3>
                      <p>+91 98765 43210</p>
                    </div>
                  </div>

                  <div className="popup-contact-card">
                    <div className="contact-card-icon">✉️</div>

                    <div>
                      <h3>Email</h3>
                      <p>support@eventhub.com</p>
                    </div>
                  </div>

                  <div className="popup-contact-card">
                    <div className="contact-card-icon">🏢</div>

                    <div>
                      <h3>Office</h3>
                      <p>
                        EventHub Office
                        <br />
                        Karnataka, India
                      </p>
                    </div>
                  </div>
                </div>

                <div className="contact-note">
                  <strong>💡 Need to plan an event?</strong>

                  <p>
                    Register or login to EventHub and submit your event
                    requirements. Our Event Manager will review your request and
                    contact you.
                  </p>
                </div>
              </div>
            )}
          </div>
        </div>
      )}
    </>
  );
}

export default Navbar;
