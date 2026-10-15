
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import API_BASE_URL from "../api/api";
import collegeFestImage from "../assets/images/collegefest.jpg";
import "../components/CollegeFest.css";




import {
  FaGraduationCap,
  FaMusic,
  FaTheaterMasks,
  FaLaptopCode,
  FaTrophy,
  FaUsers,
} from "react-icons/fa";

function CollegeFest() {
  return (
    <>
      {/* Banner */}

      <section className="fest-banner">
        <div className="container">
          <h1>College Fest</h1>

          <p>
            Make your college festival unforgettable with exciting cultural
            events, technical competitions and entertainment organized by
            EventHub.
          </p>
        </div>
      </section>

      {/* About */}

      <section className="fest-about">
        <div className="container">
          <div className="row align-items-center">
            <div className="col-lg-6">
              <img
                src="https://images.unsplash.com/photo-1523580846011-d3a5bc25702b?w=900"
                alt="College Fest"
                className="img-fluid rounded-4 shadow"
              />
            </div>

            <div className="col-lg-6">
              <span className="section-tag">COLLEGE FESTIVAL</span>

              <h2>Celebrate Talent & Creativity</h2>

              <p>
                EventHub organizes annual college festivals, technical fests,
                cultural programs, freshers' parties and farewell events with
                complete planning and execution.
              </p>

              <p>
                We manage stage setup, competitions, celebrity guests,
                registrations, sponsorships, sound systems and event
                coordination to make your campus event a huge success.
              </p>
            </div>
          </div>
        </div>
      </section>

      {/* Services */}

      <section className="fest-services">
        <div className="container">
          <div className="section-title">
            <h2>Festival Services</h2>

            <p>Everything required for an amazing college festival.</p>
          </div>

          <div className="row g-4">
            <div className="col-md-6 col-lg-4">
              <div className="service-card">
                <FaMusic />

                <h4>Cultural Events</h4>

                <p>Dance, singing, fashion shows and music performances.</p>
              </div>
            </div>

            <div className="col-md-6 col-lg-4">
              <div className="service-card">
                <FaLaptopCode />

                <h4>Technical Events</h4>

                <p>Coding contests, hackathons and technical workshops.</p>
              </div>
            </div>

            <div className="col-md-6 col-lg-4">
              <div className="service-card">
                <FaTheaterMasks />

                <h4>Stage Shows</h4>

                <p>Drama, comedy acts and live entertainment programs.</p>
              </div>
            </div>

            <div className="col-md-6 col-lg-4">
              <div className="service-card">
                <FaUsers />

                <h4>Student Registration</h4>

                <p>Smooth registration and participant management.</p>
              </div>
            </div>

            <div className="col-md-6 col-lg-4">
              <div className="service-card">
                <FaTrophy />

                <h4>Awards & Prizes</h4>

                <p>Trophy, medal and certificate arrangements.</p>
              </div>
            </div>

            <div className="col-md-6 col-lg-4">
              <div className="service-card">
                <FaGraduationCap />

                <h4>Campus Management</h4>

                <p>Complete event planning and on-campus coordination.</p>
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Packages */}

      <section className="fest-packages">
        <div className="container">
          <div className="section-title">
            <h2>College Fest Packages</h2>
          </div>

          <div className="row g-4">
            <div className="col-lg-4">
              <div className="package-card">
                <h3>Basic</h3>

                <h1>₹80,000</h1>

                <ul>
                  <li>✔ Stage Setup</li>
                  <li>✔ Sound System</li>
                  <li>✔ Registration Desk</li>
                  <li>✔ Prize Distribution</li>
                </ul>

                <button className="btn btn-warning">Book Now</button>
              </div>
            </div>

            <div className="col-lg-4">
              <div className="package-card popular">
                <span className="popular-badge">Best Choice</span>

                <h3>Premium</h3>

                <h1>₹1,75,000</h1>

                <ul>
                  <li>✔ Everything in Basic</li>
                  <li>✔ LED Stage</li>
                  <li>✔ DJ & Lighting</li>
                  <li>✔ Celebrity Host</li>
                </ul>

                <button className="btn btn-warning">Book Now</button>
              </div>
            </div>

            <div className="col-lg-4">
              <div className="package-card">
                <h3>Ultimate</h3>

                <h1>₹3,50,000</h1>

                <ul>
                  <li>✔ Celebrity Performance</li>
                  <li>✔ Live Streaming</li>
                  <li>✔ Premium Stage</li>
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

export default CollegeFest;
