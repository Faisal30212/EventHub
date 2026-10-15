import { useState } from "react";
import "./Categories.css";

import {
  FaRing,
  FaBirthdayCake,
  FaBriefcase,
  FaMicrophone,
  FaFutbol,
  FaUniversity,
} from "react-icons/fa";

function Categories() {
  const [selectedCategory, setSelectedCategory] = useState(null);

  const categories = [
    {
      icon: <FaRing />,
      title: "Wedding",
      description:
        "Complete wedding planning with venue, decoration, catering and event coordination.",

      howWeDoIt: [
        "Understand your wedding requirements and guest count.",
        "Help you choose a suitable venue and coordinate arrangements.",
        "Plan decoration, catering, stage, sound and other requirements.",
        "Our Event Manager coordinates the complete event plan with you.",
      ],

      benefits: [
        "Less stress and easier planning",
        "Professional event coordination",
        "Venue and service coordination",
        "Event plan based on your requirements",
      ],
    },

    {
      icon: <FaBirthdayCake />,
      title: "Birthday",
      description:
        "Creative birthday celebrations with themes, decoration, catering and entertainment.",

      howWeDoIt: [
        "Understand your birthday theme, date and guest requirements.",
        "Help select a suitable venue according to your event size.",
        "Coordinate decoration, catering and entertainment arrangements.",
        "Prepare the event plan according to your preferences.",
      ],

      benefits: [
        "Personalized birthday planning",
        "Creative theme and decoration coordination",
        "Convenient venue arrangements",
        "Less work for you and your family",
      ],
    },

    {
      icon: <FaBriefcase />,
      title: "Conference",
      description:
        "Professional conferences with venue, seating, audio-visual and catering arrangements.",

      howWeDoIt: [
        "Understand the purpose, date and expected number of participants.",
        "Help select a suitable conference venue.",
        "Coordinate seating, audio-visual equipment and catering.",
        "Manage the event arrangements according to your requirements.",
      ],

      benefits: [
        "Professional event setup",
        "Organized venue and seating",
        "Audio-visual coordination",
        "Smooth event management",
      ],
    },

    {
      icon: <FaMicrophone />,
      title: "Concert",
      description:
        "Music and entertainment events with stage, sound, lighting and venue coordination.",

      howWeDoIt: [
        "Understand your concert requirements and expected audience.",
        "Help coordinate a suitable venue for the event.",
        "Plan stage, sound, lighting and other event arrangements.",
        "Coordinate the different requirements for a smooth event.",
      ],

      benefits: [
        "Complete event coordination",
        "Stage and sound arrangements",
        "Suitable venue planning",
        "Better organization and less stress",
      ],
    },

    {
      icon: <FaFutbol />,
      title: "Sports",
      description:
        "Sports tournaments and events with venue coordination and complete event management.",

      howWeDoIt: [
        "Understand the type of sport and expected participants.",
        "Help coordinate a suitable sports venue.",
        "Plan event arrangements according to the tournament requirements.",
        "Coordinate the different event activities and arrangements.",
      ],

      benefits: [
        "Suitable venue coordination",
        "Organized tournament planning",
        "Professional event support",
        "Easier management of event arrangements",
      ],
    },

    {
      icon: <FaUniversity />,
      title: "College Fest",
      description:
        "Cultural and technical college festivals with stage, activities, venue and event coordination.",

      howWeDoIt: [
        "Understand the type of fest and planned activities.",
        "Coordinate venue and event arrangements according to the expected crowd.",
        "Help organize stage, sound and other requirements.",
        "Coordinate the overall event plan with the organizers.",
      ],

      benefits: [
        "Better organization of college events",
        "Stage and venue coordination",
        "Support for cultural and technical activities",
        "Less workload for student organizers",
      ],
    },
  ];

  const closePopup = () => {
    setSelectedCategory(null);
  };

  return (
    <section className="categories">
      <div className="container">
        {/* SECTION HEADING */}
        <div className="section-heading">
          <span>OUR SERVICES</span>

          <h2>Explore Event Categories</h2>

          <p>
            Explore our event services and learn how EventHub can help you
            organize your special event.
          </p>
        </div>

        {/* CATEGORY CARDS */}
        <div className="category-grid">
          {categories.map((item, index) => (
            <div className="category-card" key={index}>
              <div className="category-icon">{item.icon}</div>

              <h3>{item.title}</h3>

              <p>{item.description}</p>

              <button type="button" onClick={() => setSelectedCategory(item)}>
                Learn More →
              </button>
            </div>
          ))}
        </div>
      </div>

      {/* INFORMATION POPUP */}
      {selectedCategory && (
        <div className="category-info-overlay" onClick={closePopup}>
          <div
            className="category-info-popup"
            onClick={(e) => e.stopPropagation()}
          >
            {/* CLOSE BUTTON */}
            <button
              type="button"
              className="category-info-close"
              onClick={closePopup}
              aria-label="Close"
            >
              ×
            </button>

            {/* POPUP HEADER */}
            <div className="category-info-header">
              <div className="category-info-icon">{selectedCategory.icon}</div>

              <div>
                <span>EVENTHUB SERVICES</span>

                <h2>{selectedCategory.title} Events</h2>
              </div>
            </div>

            <p className="category-info-intro">
              {selectedCategory.description}
            </p>

            {/* HOW WE DO IT */}
            <div className="category-info-section">
              <h3>How We Do It</h3>

              <div className="category-info-list">
                {selectedCategory.howWeDoIt.map((item, index) => (
                  <div className="category-info-item" key={index}>
                    <span>{index + 1}</span>
                    <p>{item}</p>
                  </div>
                ))}
              </div>
            </div>

            {/* BENEFITS */}
            <div className="category-info-section">
              <h3>Benefits You Get</h3>

              <div className="category-benefits-grid">
                {selectedCategory.benefits.map((benefit, index) => (
                  <div className="category-benefit" key={index}>
                    <span>✓</span>
                    <p>{benefit}</p>
                  </div>
                ))}
              </div>
            </div>

            {/* BOTTOM NOTE */}
            <div className="category-info-note">
              <strong>EventHub — Professional Event Management</strong>

              <p>
                We understand your requirements and coordinate the event
                according to your needs.
              </p>
            </div>
          </div>
        </div>
      )}
    </section>
  );
}

export default Categories;
