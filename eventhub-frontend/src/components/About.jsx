import "./About.css";
import { FaUsers, FaAward, FaHandshake, FaMapMarkedAlt } from "react-icons/fa";

function About() {
  return (
    <>
      {/* Banner */}

      <section className="about-banner">
        <div className="container">
          <h1>About EventHub</h1>

          <p>
            Making Every Celebration Memorable with Professional Event Planning
            Across Karnataka.
          </p>
        </div>
      </section>

      {/* About Company */}

      <section className="about-company">
        <div className="container">
          <div className="row align-items-center">
            <div className="col-lg-6">
              <img
                src="https://images.unsplash.com/photo-1511578314322-379afb476865?w=900"
                className="img-fluid rounded-4 shadow"
                alt="About EventHub"
              />
            </div>

            <div className="col-lg-6">
              <span className="about-tag">ABOUT EVENTHUB</span>

              <h2>Creating Memorable Events Since 2026</h2>

              <p>
                EventHub is a complete Event Management Platform that
                specializes in organizing weddings, birthdays, conferences,
                concerts, sports tournaments and college festivals.
              </p>

              <p>
                We believe every celebration deserves careful planning, creative
                ideas and flawless execution. Our experienced team works closely
                with every client to transform their vision into reality.
              </p>

              <button className="btn btn-warning btn-lg mt-3">
                Contact Us
              </button>
            </div>
          </div>
        </div>
      </section>

      {/* Mission Vision */}

      <section className="mission-section">
        <div className="container">
          <div className="row g-4">
            <div className="col-lg-6">
              <div className="mission-card">
                <h3>Our Mission</h3>

                <p>
                  To provide premium event management services with innovative
                  ideas, professional planning and memorable customer
                  experiences.
                </p>
              </div>
            </div>

            <div className="col-lg-6">
              <div className="mission-card">
                <h3>Our Vision</h3>

                <p>
                  To become Karnataka's most trusted event planning company by
                  delivering world-class celebrations for every customer.
                </p>
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Why Us */}

      <section className="about-features">
        <div className="container">
          <div className="section-title">
            <h2>Why EventHub?</h2>

            <p>Everything you need to organize unforgettable events.</p>
          </div>

          <div className="row g-4">
            <div className="col-md-6 col-lg-3">
              <div className="feature-box">
                <FaUsers />

                <h4>Professional Team</h4>

                <p>Experienced planners for every type of event.</p>
              </div>
            </div>

            <div className="col-md-6 col-lg-3">
              <div className="feature-box">
                <FaAward />

                <h4>Premium Quality</h4>

                <p>High-quality decoration and event execution.</p>
              </div>
            </div>

            <div className="col-md-6 col-lg-3">
              <div className="feature-box">
                <FaHandshake />

                <h4>Trusted Service</h4>

                <p>Thousands of happy customers across Karnataka.</p>
              </div>
            </div>

            <div className="col-md-6 col-lg-3">
              <div className="feature-box">
                <FaMapMarkedAlt />

                <h4>9 Branches</h4>

                <p>Easily connect with your nearest EventHub office.</p>
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Statistics */}

      <section className="about-stats">
        <div className="container">
          <div className="row text-center">
            <div className="col-6 col-lg-3">
              <div className="stat-box">
                <h2>2500+</h2>

                <p>Events</p>
              </div>
            </div>

            <div className="col-6 col-lg-3">
              <div className="stat-box">
                <h2>10000+</h2>

                <p>Happy Clients</p>
              </div>
            </div>

            <div className="col-6 col-lg-3">
              <div className="stat-box">
                <h2>9</h2>

                <p>Branches</p>
              </div>
            </div>

            <div className="col-6 col-lg-3">
              <div className="stat-box">
                <h2>4.9★</h2>

                <p>Customer Rating</p>
              </div>
            </div>
          </div>
        </div>
      </section>
    </>
  );
}

export default About;
