import { useNavigate } from "react-router-dom";
import "./Events.css";

function Events() {
  const navigate = useNavigate();

  const events = [
    {
      name: "Wedding",
      icon: "💍",
      description:
        "Complete wedding planning including venue, decoration, catering and event management.",
      route: "/wedding",
    },
    {
      name: "Birthday",
      icon: "🎂",
      description:
        "Beautiful birthday celebrations with themes, decoration, catering and complete management.",
      route: "/birthday",
    },
    {
      name: "Conference",
      icon: "💼",
      description:
        "Professional conferences with venue arrangements, seating, equipment and event coordination.",
      route: "/conference",
    },
    {
      name: "Concert",
      icon: "🎵",
      description:
        "Concert planning with venue management, stage setup, sound and event coordination.",
      route: "/concert",
    },
    {
      name: "Sports",
      icon: "🏆",
      description:
        "Sports event planning including venue arrangements, coordination and event management.",
      route: "/sports",
    },
    {
      name: "College Fest",
      icon: "🎓",
      description:
        "Complete college fest management for cultural, technical and entertainment events.",
      route: "/collegefest",
    },
  ];

  return (
    <div className="events-page">
      <div className="events-header">
        <h1>Our Events</h1>

        <p>Choose the type of event you want EventHub to organize.</p>
      </div>

      <div className="events-grid">
        {events.map((event) => (
          <div className="event-card" key={event.name}>
            <div className="event-icon">{event.icon}</div>

            <h2>{event.name}</h2>

            <p>{event.description}</p>

            <div className="event-card-buttons">
              {/* EVENT INFORMATION */}
              <button
                type="button"
                className="learn-more-button"
                onClick={() =>
                  navigate(
                    `/event-info/${event.name
                      .toLowerCase()
                      .replace(/\s+/g, "-")}`,
                  )
                }
              >
                Learn More
              </button>

              {/* OPEN BOOKING / PLANNING FORM */}
              <button
                type="button"
                className="plan-event-button"
                onClick={() => navigate("/bookings")}
              >
                Plan My Event
              </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Events;
