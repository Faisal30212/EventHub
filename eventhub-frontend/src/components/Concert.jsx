import "./Concert.css";
import {
  FaMusic,
  FaGuitar,
  FaMicrophone,
  FaTicketAlt,
  FaUsers,
  FaLightbulb,
} from "react-icons/fa";

function Concert() {
  return (
    <>
      {/* Banner */}

      <section className="concert-banner">
        <div className="container">
          <h1>Live Concerts</h1>

          <p>
            Experience unforgettable live music performances with world-class
            sound, lighting and event management by EventHub.
          </p>
        </div>
      </section>

      {/* About */}

      <section className="concert-about">
        <div className="container">
          <div className="row align-items-center">
            <div className="col-lg-6">
              <img
                src="https://images.unsplash.com/photo-1501386761578-eac5c94b800a?w=900"
                alt="Concert"
                className="img-fluid rounded-4 shadow"
              />
            </div>

            <div className="col-lg-6">
              <span className="section-tag">LIVE ENTERTAINMENT</span>

              <h2>Creating Amazing Concert Experiences</h2>

              <p>
                EventHub organizes concerts for colleges, companies, festivals
                and public events with professional planning, security and
                technical support.
              </p>

              <p>
                From artist coordination to stage setup, lighting, ticket
                management and crowd control, we manage every detail for a
                successful event.
              </p>
            </div>
          </div>
        </div>
      </section>

      {/* Services */}

      <section className="concert-services">
        <div className="container">
          <div className="section-title">
            <h2>Concert Services</h2>

            <p>Everything required for a successful live concert.</p>
          </div>

          <div className="row g-4">
            <div className="col-md-6 col-lg-4">
              <div className="service-card">
                <FaMusic />

                <h4>Live Performances</h4>

                <p>Professional artist and band management.</p>
              </div>
            </div>

            <div className="col-md-6 col-lg-4">
              <div className="service-card">
                <FaMicrophone />

                <h4>Sound System</h4>

                <p>Premium speakers and high-quality audio setup.</p>
              </div>
            </div>

            <div className="col-md-6 col-lg-4">
              <div className="service-card">
                <FaLightbulb />

                <h4>Lighting Effects</h4>

                <p>Modern LED lighting and special stage effects.</p>
              </div>
            </div>

            <div className="col-md-6 col-lg-4">
              <div className="service-card">
                <FaTicketAlt />

                <h4>Ticket Management</h4>

                <p>Online and offline ticket booking support.</p>
              </div>
            </div>

            <div className="col-md-6 col-lg-4">
              <div className="service-card">
                <FaUsers />

                <h4>Crowd Management</h4>

                <p>Security, entry management and audience assistance.</p>
              </div>
            </div>

            <div className="col-md-6 col-lg-4">
              <div className="service-card">
                <FaGuitar />

                <h4>Stage Setup</h4>

                <p>Premium stage design with modern equipment.</p>
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Packages */}

      <section className="concert-packages">
        <div className="container">
          <div className="section-title">
            <h2>Concert Packages</h2>
          </div>

          <div className="row g-4">
            <div className="col-lg-4">
              <div className="package-card">
                <h3>Basic</h3>

                <h1>₹1,00,000</h1>

                <ul>
                  <li>✔ Small Stage</li>
                  <li>✔ Sound System</li>
                  <li>✔ Lighting</li>
                  <li>✔ Security</li>
                </ul>

                <button className="btn btn-warning">Book Now</button>
              </div>
            </div>

            <div className="col-lg-4">
              <div className="package-card popular">
                <span className="popular-badge">Best Choice</span>

                <h3>Premium</h3>

                <h1>₹2,50,000</h1>

                <ul>
                  <li>✔ Everything in Basic</li>
                  <li>✔ LED Screens</li>
                  <li>✔ Artist Management</li>
                  <li>✔ Ticket Support</li>
                </ul>

                <button className="btn btn-warning">Book Now</button>
              </div>
            </div>

            <div className="col-lg-4">
              <div className="package-card">
                <h3>Ultimate</h3>

                <h1>₹5,00,000</h1>

                <ul>
                  <li>✔ Premium Stage</li>
                  <li>✔ VIP Area</li>
                  <li>✔ Live Streaming</li>
                  <li>✔ Complete Event Management</li>
                </ul>

                <button className="btn btn-warning">Book Now</button>
              </div>
            </div>
          </div>
        </div>
      </section>
    </>
  );
}

export default Concert;
