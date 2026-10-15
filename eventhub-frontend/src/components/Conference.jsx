import "./Conference.css";
import {
  FaUsers,
  FaMicrophone,
  FaLaptop,
  FaProjectDiagram,
  FaWifi,
  FaCoffee,
} from "react-icons/fa";

function Conference() {
  return (
    <>
      {/* Banner */}

      <section className="conference-banner">

        <div className="container">

          <h1>Business Conferences</h1>

          <p>
            Organize successful corporate meetings, seminars and
            conferences with EventHub's professional event management
            services.
          </p>

        </div>

      </section>

      {/* About */}

      <section className="conference-about">

        <div className="container">

          <div className="row align-items-center">

            <div className="col-lg-6">

              <img
                src="https://images.unsplash.com/photo-1511578314322-379afb476865?w=900"
                alt="Conference"
                className="img-fluid rounded-4 shadow"
              />

            </div>

            <div className="col-lg-6">

              <span className="section-tag">
                CORPORATE EVENTS
              </span>

              <h2>Professional Conference Management</h2>

              <p>
                EventHub provides complete conference planning for
                businesses, educational institutions and organizations.
              </p>

              <p>
                From venue selection and speaker management to technical
                support and hospitality, our experienced team ensures
                every conference runs smoothly.
              </p>

            </div>

          </div>

        </div>

      </section>

      {/* Services */}

      <section className="conference-services">

        <div className="container">

          <div className="section-title">

            <h2>Conference Services</h2>

            <p>
              Everything required for a successful business event.
            </p>

          </div>

          <div className="row g-4">

            <div className="col-md-6 col-lg-4">

              <div className="service-card">

                <FaMicrophone />

                <h4>Speaker Management</h4>

                <p>
                  Professional arrangements for keynote speakers and
                  guest sessions.
                </p>

              </div>

            </div>

            <div className="col-md-6 col-lg-4">

              <div className="service-card">

                <FaLaptop />

                <h4>Audio & Visual</h4>

                <p>
                  High-quality projectors, LED screens and sound systems.
                </p>

              </div>

            </div>

            <div className="col-md-6 col-lg-4">

              <div className="service-card">

                <FaUsers />

                <h4>Guest Registration</h4>

                <p>
                  Smooth attendee registration and welcome assistance.
                </p>

              </div>

            </div>

            <div className="col-md-6 col-lg-4">

              <div className="service-card">

                <FaProjectDiagram />

                <h4>Event Planning</h4>

                <p>
                  Complete scheduling and coordination for business
                  events.
                </p>

              </div>

            </div>

            <div className="col-md-6 col-lg-4">

              <div className="service-card">

                <FaWifi />

                <h4>Technical Support</h4>

                <p>
                  Fast internet, live streaming and technical assistance.
                </p>

              </div>

            </div>

            <div className="col-md-6 col-lg-4">

              <div className="service-card">

                <FaCoffee />

                <h4>Catering</h4>

                <p>
                  Refreshments, lunch and coffee breaks for attendees.
                </p>

              </div>

            </div>

          </div>

        </div>

      </section>

      {/* Packages */}

      <section className="conference-packages">

        <div className="container">

          <div className="section-title">

            <h2>Conference Packages</h2>

          </div>

          <div className="row g-4">

            <div className="col-lg-4">

              <div className="package-card">

                <h3>Basic</h3>

                <h1>₹75,000</h1>

                <ul>

                  <li>✔ Meeting Hall</li>
                  <li>✔ Sound System</li>
                  <li>✔ Projector</li>
                  <li>✔ Registration Desk</li>

                </ul>

                <button className="btn btn-warning">
                  Book Now
                </button>

              </div>

            </div>

            <div className="col-lg-4">

              <div className="package-card popular">

                <span className="popular-badge">
                  Most Popular
                </span>

                <h3>Professional</h3>

                <h1>₹1,50,000</h1>

                <ul>

                  <li>✔ Everything in Basic</li>
                  <li>✔ LED Display</li>
                  <li>✔ Catering</li>
                  <li>✔ Technical Support</li>

                </ul>

                <button className="btn btn-warning">
                  Book Now
                </button>

              </div>

            </div>

            <div className="col-lg-4">

              <div className="package-card">

                <h3>Enterprise</h3>

                <h1>₹3,00,000</h1>

                <ul>

                  <li>✔ Premium Venue</li>
                  <li>✔ Live Streaming</li>
                  <li>✔ VIP Lounge</li>
                  <li>✔ Complete Event Management</li>

                </ul>

                <button className="btn btn-warning">
                  Book Now
                </button>

              </div>

            </div>

          </div>

        </div>

      </section>

    </>
  );
}

export default Conference;