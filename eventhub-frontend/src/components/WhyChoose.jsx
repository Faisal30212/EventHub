import "./WhyChoose.css";
import {
  FaUsers,
  FaMapMarkedAlt,
  FaHandshake,
  FaClock,
  FaAward,
  FaHeadset,
} from "react-icons/fa";

function WhyChoose() {
  const features = [
    {
      icon: <FaUsers />,
      title: "Experienced Team",
      description:
        "Professional event planners with years of experience in organizing memorable events.",
    },
    {
      icon: <FaMapMarkedAlt />,
      title: "9 Offices Across Karnataka",
      description:
        "Visit your nearest EventHub branch for personalized planning and consultation.",
    },
    {
      icon: <FaHandshake />,
      title: "Trusted Service",
      description:
        "Thousands of happy customers trust EventHub for weddings, conferences and celebrations.",
    },
    {
      icon: <FaClock />,
      title: "On-Time Delivery",
      description:
        "We carefully plan every event and ensure everything is completed on schedule.",
    },
    {
      icon: <FaAward />,
      title: "Premium Quality",
      description:
        "Elegant decoration, professional coordination and high-quality event management.",
    },
    {
      icon: <FaHeadset />,
      title: "24×7 Support",
      description:
        "Our team is always available to assist you before, during and after your event.",
    },
  ];

  return (
    <section className="why-section">
      <div className="container">
        <div className="why-heading">
          <span>WHY CHOOSE US</span>

          <h2>Why Choose EventHub?</h2>

          <p>
            We provide complete event planning solutions with creativity,
            professionalism and dedication.
          </p>
        </div>

        <div className="row g-4">
          {features.map((item, index) => (
            <div className="col-md-6 col-lg-4" key={index}>
              <div className="why-card">
                <div className="why-icon">{item.icon}</div>

                <h4>{item.title}</h4>

                <p>{item.description}</p>
              </div>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
}

export default WhyChoose;
