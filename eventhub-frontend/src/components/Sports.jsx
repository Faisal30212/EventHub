import "./Sports.css";
import {
  FaFutbol,
  FaRunning,
  FaMedal,
  FaUsers,
  FaBullhorn,
  FaTrophy,
} from "react-icons/fa";

function Sports() {
  return (
    <>
      {/* Banner */}

      <section className="sports-banner">
        <div className="container">
          <h1>Sports Events</h1>

          <p>
            Organize exciting sports tournaments and competitions with
            EventHub's professional sports event management services.
          </p>
        </div>
      </section>

      {/* About */}

      <section className="sports-about">
        <div className="container">
          <div className="row align-items-center">
            <div className="col-lg-6">
              <img
                src="https://images.unsplash.com/photo-1517649763962-0c623066013b?w=900"
                alt="Sports Event"
                className="img-fluid rounded-4 shadow"
              />
            </div>

            <div className="col-lg-6">
              <span className="section-tag">SPORTS MANAGEMENT</span>

              <h2>Professional Sports Event Planning</h2>

              <p>
                EventHub organizes cricket, football, volleyball, badminton,
                athletics and many other sporting events for schools, colleges,
                companies and organizations.
              </p>

              <p>
                We manage everything from venue preparation and player
                registration to referees, scoreboards, crowd management and
                prize distribution.
              </p>
            </div>
          </div>
        </div>
      </section>

      {/* Services */}

      <section className="sports-services">
        <div className="container">
          <div className="section-title">
            <h2>Sports Services</h2>

            <p>Complete solutions for successful tournaments.</p>
          </div>

          <div className="row g-4">
            <div className="col-md-6 col-lg-4">
              <div className="service-card">
                <FaRunning />

                <h4>Tournament Planning</h4>

                <p>Complete planning for sports competitions and leagues.</p>
              </div>
            </div>

            <div className="col-md-6 col-lg-4">
              <div className="service-card">
                <FaUsers />

                <h4>Player Registration</h4>

                <p>Smooth participant registration and scheduling.</p>
              </div>
            </div>

            <div className="col-md-6 col-lg-4">
              <div className="service-card">
                <FaBullhorn />

                <h4>Announcements</h4>

                <p>Professional sound system and event announcements.</p>
              </div>
            </div>

            <div className="col-md-6 col-lg-4">
              <div className="service-card">
                <FaFutbol />

                <h4>Ground Setup</h4>

                <p>Sports equipment and venue preparation.</p>
              </div>
            </div>

            <div className="col-md-6 col-lg-4">
              <div className="service-card">
                <FaMedal />

                <h4>Prize Distribution</h4>

                <p>Medals, trophies and award ceremony arrangements.</p>
              </div>
            </div>

            <div className="col-md-6 col-lg-4">
              <div className="service-card">
                <FaTrophy />

                <h4>Championship Support</h4>

                <p>Complete management for large sports tournaments.</p>
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Packages */}

      <section className="sports-packages">
        <div className="container">
          <div className="section-title">
            <h2>Sports Packages</h2>
          </div>

          <div className="row g-4">
            <div className="col-lg-4">
              <div className="package-card">
                <h3>Basic</h3>

                <h1>₹50,000</h1>

                <ul>
                  <li>✔ Ground Setup</li>
                  <li>✔ Sound System</li>
                  <li>✔ Registration Desk</li>
                  <li>✔ Prize Distribution</li>
                </ul>

                <button className="btn btn-warning">Book Now</button>
              </div>
            </div>

            <div className="col-lg-4">
              <div className="package-card popular">
                <span className="popular-badge">Most Popular</span>

                <h3>Professional</h3>

                <h1>₹1,20,000</h1>

                <ul>
                  <li>✔ Everything in Basic</li>
                  <li>✔ Live Scoreboard</li>
                  <li>✔ Referee Support</li>
                  <li>✔ Medical Assistance</li>
                </ul>

                <button className="btn btn-warning">Book Now</button>
              </div>
            </div>

            <div className="col-lg-4">
              <div className="package-card">
                <h3>Championship</h3>

                <h1>₹2,50,000</h1>

                <ul>
                  <li>✔ Multi-Day Tournament</li>
                  <li>✔ Live Streaming</li>
                  <li>✔ VIP Seating</li>
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

export default Sports;
