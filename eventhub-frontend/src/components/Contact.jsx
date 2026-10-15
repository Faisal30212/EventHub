import "./Contact.css";
import {
  FaPhoneAlt,
  FaEnvelope,
  FaLocationDot,
  FaClock,
} from "react-icons/fa6";

function Contact() {
  return (
    <>
      {/* Banner */}

      <section className="contact-banner">
        <div className="container">
          <h1>Contact Us</h1>

          <p>
            We'd love to hear from you. Get in touch with EventHub and let's
            make your next event unforgettable.
          </p>
        </div>
      </section>

      {/* Contact Section */}

      <section className="contact-section">
        <div className="container">
          <div className="row g-5">
            {/* Contact Form */}

            <div className="col-lg-7">
              <div className="contact-form">
                <h2>Send Us a Message</h2>

                <form>
                  <div className="row">
                    <div className="col-md-6 mb-4">
                      <input
                        type="text"
                        className="form-control"
                        placeholder="Full Name"
                      />
                    </div>

                    <div className="col-md-6 mb-4">
                      <input
                        type="email"
                        className="form-control"
                        placeholder="Email Address"
                      />
                    </div>
                  </div>

                  <div className="mb-4">
                    <input
                      type="text"
                      className="form-control"
                      placeholder="Subject"
                    />
                  </div>

                  <div className="mb-4">
                    <textarea
                      rows="6"
                      className="form-control"
                      placeholder="Write your message..."
                    ></textarea>
                  </div>

                  <button className="btn btn-warning btn-lg">
                    Send Message
                  </button>
                </form>
              </div>
            </div>

            {/* Contact Info */}

            <div className="col-lg-5">
              <div className="contact-info">
                <h2>Contact Information</h2>

                <div className="info-box">
                  <FaPhoneAlt />

                  <div>
                    <h5>Phone</h5>

                    <p>+91 98765 43210</p>
                  </div>
                </div>

                <div className="info-box">
                  <FaEnvelope />

                  <div>
                    <h5>Email</h5>

                    <p>support@eventhub.com</p>
                  </div>
                </div>

                <div className="info-box">
                  <FaLocationDot />

                  <div>
                    <h5>Address</h5>

                    <p>Bengaluru, Karnataka, India</p>
                  </div>
                </div>

                <div className="info-box">
                  <FaClock />

                  <div>
                    <h5>Working Hours</h5>

                    <p>Mon - Sat : 9:00 AM - 7:00 PM</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Google Map */}

      <section className="map-section">
        <iframe
          title="EventHub Location"
          src="https://www.google.com/maps?q=Bangalore&output=embed"
          width="100%"
          height="450"
          style={{ border: 0 }}
          loading="lazy"
        ></iframe>
      </section>
    </>
  );
}

export default Contact;
