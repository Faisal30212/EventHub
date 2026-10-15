import "./FeaturedEvents.css";
import { Link } from "react-router-dom";
import events from "../data/events";

function FeaturedEvents() {
  return (
    <section className="featured-section">
      <div className="container">
        <div className="featured-heading">
          <span>POPULAR EVENTS</span>

          <h2>Featured Events</h2>

          <p>
            Discover our premium event planning services designed to create
            unforgettable experiences for every occasion.
          </p>
        </div>

        <div className="row g-4">
          {events.map((event) => (
            <div className="col-md-6 col-lg-4" key={event.id}>
              <div className="event-card">
                <div className="event-image-box">
                  <img
                    src={event.image}
                    alt={event.title}
                    className="event-image"
                  />

                  <div className="event-overlay">
                    <span className="price-badge">From {event.price}</span>
                  </div>
                </div>

                <div className="event-content">
                  <h3>{event.title}</h3>

                  <p>{event.description}</p>

                  <Link to={event.route} className="event-btn">
                    Plan My Event →
                  </Link>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
}

export default FeaturedEvents;
