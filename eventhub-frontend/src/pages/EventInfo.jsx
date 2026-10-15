import { useLocation, useNavigate } from "react-router-dom";
import "./EventInfo.css";

function EventInfo() {
  const location = useLocation();
  const navigate = useNavigate();

  const eventData = {
    "/wedding": {
      icon: "💍",
      title: "Wedding Events",
      subtitle: "Make your special day truly unforgettable.",
      description:
        "EventHub provides complete wedding event management according to your requirements, guest count, venue preferences and budget.",
      services: [
        "Venue selection and arrangement",
        "Wedding decoration and theme setup",
        "Catering and food arrangements",
        "Stage, lighting and sound setup",
        "Photography and entertainment coordination",
        "Guest management and event-day coordination",
      ],
      suitable: [
        "Engagement ceremonies",
        "Wedding ceremonies",
        "Wedding receptions",
        "Pre-wedding functions",
      ],
      price: "₹75,000",
      button: "Plan My Wedding →",
    },

    "/birthday": {
      icon: "🎂",
      title: "Birthday Events",
      subtitle: "Celebrate your special day with unforgettable memories.",
      description:
        "EventHub helps you organize memorable birthday celebrations with creative themes, decoration, catering and complete event coordination.",
      services: [
        "Birthday theme and decoration",
        "Venue selection and arrangement",
        "Catering and food arrangements",
        "Cake and celebration setup",
        "Music and entertainment coordination",
        "Guest management and event coordination",
      ],
      suitable: [
        "Kids birthdays",
        "Teenage birthdays",
        "Adult birthdays",
        "Milestone celebrations",
      ],
      price: "₹25,000",
      button: "Plan My Birthday →",
    },

    "/conference": {
      icon: "💼",
      title: "Conference Events",
      subtitle: "Professional events planned with complete coordination.",
      description:
        "EventHub organizes professional conferences with suitable venues, seating arrangements, audio-visual facilities, catering and event coordination.",
      services: [
        "Conference venue selection",
        "Professional seating arrangements",
        "Audio-visual equipment coordination",
        "Stage and presentation setup",
        "Catering and refreshments",
        "Complete event-day coordination",
      ],
      suitable: [
        "Business conferences",
        "Corporate meetings",
        "Seminars",
        "Professional gatherings",
      ],
      price: "₹55,000",
      button: "Plan My Conference →",
    },

    "/concert": {
      icon: "🎵",
      title: "Concert Events",
      subtitle: "Create an exciting entertainment experience.",
      description:
        "EventHub manages concerts and entertainment events with venue arrangements, stage setup, sound, lighting and complete event coordination.",
      services: [
        "Concert venue selection",
        "Stage and backdrop setup",
        "Sound system coordination",
        "Lighting arrangements",
        "Entertainment coordination",
        "Audience and event-day management",
      ],
      suitable: [
        "Music concerts",
        "Live performances",
        "College concerts",
        "Entertainment programs",
      ],
      price: "₹95,000",
      button: "Plan My Concert →",
    },

    "/sports": {
      icon: "🏆",
      title: "Sports Events",
      subtitle: "Plan competitive events with smooth coordination.",
      description:
        "EventHub helps organize sports events and tournaments with suitable venues, facilities, arrangements and complete event management.",
      services: [
        "Sports venue selection",
        "Tournament area arrangement",
        "Participant coordination",
        "Equipment and facility arrangements",
        "Refreshment arrangements",
        "Complete event coordination",
      ],
      suitable: [
        "Sports tournaments",
        "College sports events",
        "Corporate sports events",
        "Championship events",
      ],
      price: "₹70,000",
      button: "Plan My Sports Event →",
    },

    "/collegefest": {
      icon: "🎓",
      title: "College Fest",
      subtitle: "Make your college festival exciting and memorable.",
      description:
        "EventHub provides complete college fest management for cultural and technical events, including venue, stage, activities and event coordination.",
      services: [
        "College fest venue arrangement",
        "Stage and decoration setup",
        "Technical event coordination",
        "Cultural program coordination",
        "Sound and lighting arrangements",
        "Complete fest-day management",
      ],
      suitable: [
        "Cultural fests",
        "Technical fests",
        "College celebrations",
        "Inter-college events",
      ],
      price: "₹75,000",
      button: "Plan My College Fest →",
    },
  };

  const event = eventData[location.pathname] || eventData["/wedding"];

  return (
    <div className="event-info-page">

      {/* Header */}
      <div className="event-info-header">
        <span className="event-info-label">EVENTHUB EVENT MANAGEMENT</span>

        <h1>
          {event.title} <span className="event-main-icon">{event.icon}</span>
        </h1>

        <p>{event.subtitle}</p>
      </div>

      <div className="event-info-container">

        {/* About */}
        <section className="event-info-section">
          <span className="event-section-number">01</span>

          <div>
            <h2>About {event.title.replace(" Events", "")}</h2>

            <p className="event-info-description">
              {event.description}
            </p>
          </div>
        </section>

        {/* Services */}
        <section className="event-info-section">
          <span className="event-section-number">02</span>

          <div className="event-info-content">
            <h2>What EventHub Provides</h2>

            <div className="event-service-list">
              {event.services.map((service, index) => (
                <div className="event-service-item" key={index}>
                  <span className="check-icon">✓</span>
                  <span>{service}</span>
                </div>
              ))}
            </div>
          </div>
        </section>

        {/* Suitable For */}
        <section className="event-info-section">
          <span className="event-section-number">03</span>

          <div className="event-info-content">
            <h2>Suitable For</h2>

            <div className="event-suitable-list">
              {event.suitable.map((item, index) => (
                <span key={index} className="suitable-item">
                  {item}
                </span>
              ))}
            </div>
          </div>
        </section>

        {/* Package */}
        <section className="event-package-box">

          <div>
            <span className="package-label">STARTING PACKAGE</span>

            <h2>{event.price}</h2>

            <p>
              This is the starting event management package.
              The final quotation will be prepared by our Event Manager
              after discussing your requirements.
            </p>
          </div>

          <button
            type="button"
            onClick={() => navigate("/bookings")}
          >
            {event.button}
          </button>

        </section>

        {/* Process */}
        <section className="event-process">

          <span className="event-section-number">04</span>

          <div className="event-info-content">
            <h2>How It Works</h2>

            <div className="event-process-grid">

              <div>
                <strong>1</strong>
                <h3>Choose Your Event</h3>
                <p>Select the event type you want EventHub to organize.</p>
              </div>

              <div>
                <strong>2</strong>
                <h3>Choose Venue</h3>
                <p>Select your city and preferred EventHub venue.</p>
              </div>

              <div>
                <strong>3</strong>
                <h3>Share Requirements</h3>
                <p>Tell us your date, time, guest count and requirements.</p>
              </div>

              <div>
                <strong>4</strong>
                <h3>Meet Our Manager</h3>
                <p>
                  Our Event Manager discusses your requirements and prepares
                  the final quotation.
                </p>
              </div>

            </div>
          </div>

        </section>

        <button
          type="button"
          className="event-back-button"
          onClick={() => navigate("/")}
        >
          ← Back to Home
        </button>

      </div>
    </div>
  );
}

export default EventInfo;