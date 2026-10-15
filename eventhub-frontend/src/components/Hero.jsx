import "./Hero.css";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import heroImage from "../assets/images/hero.jpg";

function Hero() {
  const navigate = useNavigate();

  const heroMessages = [
    {
      title: "⭐ Premium Event Planning",
      desc: "Luxury weddings, birthdays, conferences and more.",
    },
    {
      title: "📍 9 Offices Across Karnataka",
      desc: "Find your nearest EventHub branch easily.",
    },
    {
      title: "👨‍💼 Dedicated Event Manager",
      desc: "One expert will guide you from start to finish.",
    },
    {
      title: "🎉 2500+ Successful Events",
      desc: "Trusted by thousands of happy customers.",
    },
  ];

  const stats = [
    { number: "2500+", label: "Events" },
    { number: "10K+", label: "Customers" },
    { number: "9", label: "Offices" },
    { number: "4.9★", label: "Rating" },
  ];

  const [currentMessage, setCurrentMessage] = useState(0);
  const [showCard, setShowCard] = useState(true);

  useEffect(() => {
    const hideTimer = setTimeout(() => {
      setShowCard(false);
    }, 5000);

    return () => clearTimeout(hideTimer);
  }, []);

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentMessage((prev) => (prev + 1) % heroMessages.length);
    }, 3000);

    return () => clearInterval(interval);
  }, []);

  const handlePlanMyEvent = () => {
    /*
     * Event planning starts with choosing the event type.
     *
     * Logged-in users can go directly to the planning form.
     * Guests are first sent to Login.
     */
    const storedUser = localStorage.getItem("user");

    if (storedUser) {
      navigate("/bookings");
    } else {
      navigate("/login");
    }
  };

  return (
    <section className="hero-section">
      <div className="hero-circle hero-circle-one"></div>
      <div className="hero-circle hero-circle-two"></div>

      <div className="container">
        <div className="row align-items-center min-vh-100">
          {/* LEFT SIDE */}

          <div className="col-lg-6 hero-left">
            <span className="hero-badge">EVENT MANAGEMENT PLATFORM</span>

            <h1 className="hero-title">
              Plan Every
              <span> Celebration </span>
              With Confidence.
            </h1>

            <p className="hero-description">
              EventHub helps you organize unforgettable weddings, birthdays,
              conferences, concerts, sports events and college festivals with
              complete planning and management across Karnataka.
            </p>

            <div className="hero-buttons">
              <button
                type="button"
                className="btn btn-warning btn-lg hero-btn"
                onClick={handlePlanMyEvent}
              >
                Plan My Event
              </button>
            </div>

            <div className="row mt-5">
              {stats.map((item, index) => (
                <div className="col-6 col-md-3 mb-3" key={index}>
                  <div className="stats-box">
                    <h3>{item.number}</h3>
                    <small>{item.label}</small>
                  </div>
                </div>
              ))}
            </div>
          </div>

          {/* RIGHT SIDE */}

          <div className="col-lg-6 hero-right">
            <div className="hero-image-box">
              <img src={heroImage} alt="EventHub" className="hero-image" />

              {showCard && (
                <div className="floating-card">
                  <h5>{heroMessages[currentMessage].title}</h5>

                  <p>{heroMessages[currentMessage].desc}</p>
                </div>
              )}
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}

export default Hero;
