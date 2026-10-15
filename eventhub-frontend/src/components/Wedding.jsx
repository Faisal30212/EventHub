import "./Wedding.css";
import {
  FaHeart,
  FaCamera,
  FaMusic,
  FaUtensils,
  FaGift,
  FaRing,
} from "react-icons/fa";

function Wedding() {
  return (
    <>
      {/* Hero Section */}

      <section className="wedding-banner">
        <div className="container">
          <h1>Wedding Events</h1>

          <p>
            Make your special day unforgettable with EventHub's premium wedding
            planning services.
          </p>
        </div>
      </section>

      {/* About */}

      <section className="wedding-about">
        <div className="container">
          <div className="row align-items-center">
            <div className="col-lg-6">
              <img
                src="https://images.unsplash.com/photo-1519741497674-611481863552?w=900"
                alt="Wedding"
                className="img-fluid rounded-4 shadow"
              />
            </div>

            <div className="col-lg-6">
              <span className="section-tag">WEDDING PLANNING</span>

              <h2>Your Dream Wedding Starts Here</h2>

              <p>
                EventHub plans elegant weddings with beautiful decorations,
                experienced coordinators, premium catering, entertainment,
                photography, and complete event management.
              </p>

              <p>
                From intimate ceremonies to grand receptions, we ensure every
                detail is perfectly managed so you can enjoy your big day
                stress-free.
              </p>
            </div>
          </div>
        </div>
      </section>

      {/* Services */}

      <section className="wedding-services">
        <div className="container">
          <div className="section-title">
            <h2>Wedding Services</h2>

            <p>Everything required for a perfect wedding.</p>
          </div>

          <div className="row g-4">
            <div className="col-md-6 col-lg-4">
              <div className="service-card">
                <FaRing />

                <h4>Venue Decoration</h4>

                <p>Premium floral decorations and elegant stage designs.</p>
              </div>
            </div>

            <div className="col-md-6 col-lg-4">
              <div className="service-card">
                <FaCamera />

                <h4>Photography</h4>

                <p>Professional photography and cinematic videography.</p>
              </div>
            </div>

            <div className="col-md-6 col-lg-4">
              <div className="service-card">
                <FaMusic />

                <h4>Entertainment</h4>

                <p>DJs, live bands, cultural performances and music.</p>
              </div>
            </div>

            <div className="col-md-6 col-lg-4">
              <div className="service-card">
                <FaUtensils />

                <h4>Catering</h4>

                <p>Delicious vegetarian and non-vegetarian cuisines.</p>
              </div>
            </div>

            <div className="col-md-6 col-lg-4">
              <div className="service-card">
                <FaGift />

                <h4>Guest Management</h4>

                <p>Invitations, welcome kits and complete guest support.</p>
              </div>
            </div>

            <div className="col-md-6 col-lg-4">
              <div className="service-card">
                <FaHeart />

                <h4>Wedding Planning</h4>

                <p>End-to-end planning with experienced coordinators.</p>
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Packages */}

      <section className="wedding-packages">
        <div className="container">
          <div className="section-title">
            <h2>Wedding Packages</h2>
          </div>

          <div className="row g-4">
            <div className="col-lg-4">
              <div className="package-card">
                <h3>Silver</h3>

                <h1>₹1,50,000</h1>

                <ul>
                  <li>✔ Venue Decoration</li>
                  <li>✔ Photography</li>
                  <li>✔ Catering</li>
                  <li>✔ Guest Management</li>
                </ul>

                <button className="btn btn-warning">Book Now</button>
              </div>
            </div>

            <div className="col-lg-4">
              <div className="package-card popular">
                <span className="popular-badge">Most Popular</span>

                <h3>Gold</h3>

                <h1>₹3,00,000</h1>

                <ul>
                  <li>✔ Everything in Silver</li>
                  <li>✔ Live Music</li>
                  <li>✔ Luxury Decoration</li>
                  <li>✔ Premium Photography</li>
                </ul>

                <button className="btn btn-warning">Book Now</button>
              </div>
            </div>

            <div className="col-lg-4">
              <div className="package-card">
                <h3>Platinum</h3>

                <h1>₹5,00,000</h1>

                <ul>
                  <li>✔ Complete Wedding Planning</li>
                  <li>✔ Celebrity DJ</li>
                  <li>✔ Luxury Catering</li>
                  <li>✔ VIP Guest Management</li>
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

export default Wedding;
