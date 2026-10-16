import { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import Hero from "../components/Hero";
import Categories from "../components/Categories";
import FeaturedEvents from "../components/FeaturedEvents";
import API_BASE_URL from "../api/Api.js";
import "./Home.css";

function Home() {
  const [reviews, setReviews] = useState([]);

  useEffect(() => {
    fetch(`${API_BASE_URL}/reviews`)
      .then((response) => response.json())
      .then((data) => {
        setReviews(data);
      })
      .catch((error) => {
        console.error("Error fetching reviews:", error);
      });
  }, []);

  return (
    <>
      <Navbar />

      <Hero />

      <Categories />

      <FeaturedEvents />

      {/* Customer Reviews Section */}
      <section className="home-reviews">
        <div className="home-reviews-heading">
          <h2>What Our Customers Say ⭐</h2>

          <p>See what our customers think about their EventHub experience.</p>
        </div>

        <div className="home-reviews-container">
          {reviews.length === 0 ? (
            <p className="no-reviews">No reviews available yet.</p>
          ) : (
            reviews.slice(0, 3).map((review) => (
              <div className="home-review-card" key={review.reviewId}>
                <div className="home-review-top">
                  <strong>{review.user?.fullName || "Customer"}</strong>

                  <span>{review.reviewDate}</span>
                </div>

                <div className="home-review-rating">
                  {"⭐".repeat(review.rating || 0)}
                </div>

                <p className="home-review-comment">{review.comment}</p>

                <span className="home-review-event">
                  {review.event?.eventName || "Event"}
                </span>
              </div>
            ))
          )}
        </div>

        <button
          className="home-view-reviews-button"
          onClick={() => {
            window.location.href = "/reviews";
          }}
        >
          View All Reviews →
        </button>
      </section>
    </>
  );
}

export default Home;
