import "./Testimonials.css";
import { FaStar } from "react-icons/fa";

function Testimonials() {
  const reviews = [
    {
      name: "Rahul Sharma",
      city: "Bengaluru",
      review:
        "EventHub made our wedding unforgettable. Everything was perfectly organized and stress-free.",
    },

    {
      name: "Priya N",
      city: "Mysuru",
      review:
        "Professional team, beautiful decorations and excellent coordination throughout the event.",
    },

    {
      name: "Arjun Kumar",
      city: "Hubballi",
      review:
        "Highly recommended for corporate conferences. Their planning and execution were outstanding.",
    },
  ];

  return (
    <section className="testimonial-section">
      <div className="container">
        <div className="testimonial-heading">
          <span>TESTIMONIALS</span>

          <h2>What Our Clients Say</h2>

          <p>
            Thousands of customers trust EventHub for creating memorable
            experiences across Karnataka.
          </p>
        </div>

        <div className="row g-4">
          {reviews.map((review, index) => (
            <div className="col-lg-4" key={index}>
              <div className="testimonial-card">
                <div className="stars">
                  <FaStar />
                  <FaStar />
                  <FaStar />
                  <FaStar />
                  <FaStar />
                </div>

                <p className="review-text">"{review.review}"</p>

                <h5>{review.name}</h5>

                <span>{review.city}</span>
              </div>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
}

export default Testimonials;
