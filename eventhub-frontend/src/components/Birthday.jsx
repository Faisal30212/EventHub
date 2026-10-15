import "./Birthday.css";
import {
  FaBirthdayCake,
  FaGift,
  FaCamera,
  FaMusic,
  FaSmile,
  FaIceCream,
} from "react-icons/fa";

function Birthday() {
  return (
    <>
      {/* Banner */}

      <section className="birthday-banner">
        <div className="container">
          <h1>Birthday Celebrations</h1>

          <p>
            Celebrate your special day with fun, creativity and unforgettable
            memories planned by EventHub.
          </p>
        </div>
      </section>

      {/* About */}

      <section className="birthday-about">
        <div className="container">
          <div className="row align-items-center">
            <div className="col-lg-6">
              <img
                src="https://images.unsplash.com/photo-1464349153735-7db50ed83c84?w=900"
                alt="Birthday Event"
                className="img-fluid rounded-4 shadow"
              />
            </div>

            <div className="col-lg-6">
              <span className="section-tag">BIRTHDAY EVENTS</span>

              <h2>Celebrate Every Birthday in Style</h2>

              <p>
                Whether it's a child's birthday, a sweet sixteen, an 18th
                birthday or a surprise party, EventHub creates memorable
                celebrations for every age.
              </p>

              <p>
                We handle decorations, entertainment, photography, catering,
                games and complete event management so you can simply enjoy the
                celebration.
              </p>
            </div>
          </div>
        </div>
      </section>

      {/* Services */}

      <section className="birthday-services">
        <div className="container">
          <div className="section-title">
            <h2>Our Services</h2>

            <p>Everything needed for a perfect birthday celebration.</p>
          </div>

          <div className="row g-4">
            <div className="col-md-6 col-lg-4">
              <div className="service-card">
                <FaBirthdayCake />

                <h4>Theme Decoration</h4>

                <p>Beautiful decorations based on your favorite theme.</p>
              </div>
            </div>

            <div className="col-md-6 col-lg-4">
              <div className="service-card">
                <FaGift />

                <h4>Return Gifts</h4>

                <p>Attractive gifts for every guest attending the party.</p>
              </div>
            </div>

            <div className="col-md-6 col-lg-4">
              <div className="service-card">
                <FaCamera />

                <h4>Photography</h4>

                <p>Professional photography to capture every happy moment.</p>
              </div>
            </div>

            <div className="col-md-6 col-lg-4">
              <div className="service-card">
                <FaMusic />

                <h4>DJ & Music</h4>

                <p>Dance floor, DJ and music for exciting celebrations.</p>
              </div>
            </div>

            <div className="col-md-6 col-lg-4">
              <div className="service-card">
                <FaSmile />

                <h4>Games & Fun</h4>

                <p>Fun activities and games for kids and adults.</p>
              </div>
            </div>

            <div className="col-md-6 col-lg-4">
              <div className="service-card">
                <FaIceCream />

                <h4>Food & Desserts</h4>

                <p>Delicious catering with cakes, snacks and desserts.</p>
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Packages */}

      <section className="birthday-packages">
        <div className="container">
          <div className="section-title">
            <h2>Birthday Packages</h2>
          </div>

          <div className="row g-4">
            <div className="col-lg-4">
              <div className="package-card">
                <h3>Basic</h3>

                <h1>₹20,000</h1>

                <ul>
                  <li>✔ Balloon Decoration</li>
                  <li>✔ Birthday Cake</li>
                  <li>✔ Photography</li>
                  <li>✔ Music System</li>
                </ul>

                <button className="btn btn-warning">Book Now</button>
              </div>
            </div>

            <div className="col-lg-4">
              <div className="package-card popular">
                <span className="popular-badge">Best Seller</span>

                <h3>Premium</h3>

                <h1>₹40,000</h1>

                <ul>
                  <li>✔ Everything in Basic</li>
                  <li>✔ Theme Decoration</li>
                  <li>✔ DJ & Games</li>
                  <li>✔ Return Gifts</li>
                </ul>

                <button className="btn btn-warning">Book Now</button>
              </div>
            </div>

            <div className="col-lg-4">
              <div className="package-card">
                <h3>Luxury</h3>

                <h1>₹70,000</h1>

                <ul>
                  <li>✔ Premium Decoration</li>
                  <li>✔ Live Entertainment</li>
                  <li>✔ Luxury Catering</li>
                  <li>✔ Complete Event Planning</li>
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

export default Birthday;
