import "./FAQ.css";
import { useState } from "react";
import { FaChevronDown, FaChevronUp } from "react-icons/fa";

function FAQ() {
  const faqs = [
    {
      question: "What types of events does EventHub organize?",
      answer:
        "We organize weddings, birthdays, conferences, concerts, sports tournaments and college festivals.",
    },
    {
      question: "How can I book an event?",
      answer:
        "You can browse our event packages, click the Book Now button and complete the booking process. Once the backend is connected, bookings will be stored in the database.",
    },
    {
      question: "Can I customize my event package?",
      answer:
        "Yes. We offer fully customizable packages based on your budget, venue, guest count and requirements.",
    },
    {
      question: "Do you provide catering services?",
      answer:
        "Yes. We provide both vegetarian and non-vegetarian catering with multiple menu options.",
    },
    {
      question: "Which cities do you serve?",
      answer:
        "Currently EventHub provides services across Karnataka and continues to expand to new locations.",
    },
    {
      question: "Can I cancel my booking?",
      answer:
        "Yes. Cancellation and refund policies depend on the selected package and cancellation date.",
    },
  ];

  const [activeIndex, setActiveIndex] = useState(null);

  const toggleFAQ = (index) => {
    setActiveIndex(activeIndex === index ? null : index);
  };

  return (
    <>
      {/* Banner */}

      <section className="faq-banner">
        <div className="container">
          <h1>Frequently Asked Questions</h1>

          <p>
            Find answers to the most common questions about EventHub services
            and event bookings.
          </p>
        </div>
      </section>

      {/* FAQ */}

      <section className="faq-section">
        <div className="container">
          <div className="faq-container">
            {faqs.map((faq, index) => (
              <div className="faq-item" key={index}>
                <button
                  className="faq-question"
                  onClick={() => toggleFAQ(index)}
                >
                  <span>{faq.question}</span>

                  {activeIndex === index ? <FaChevronUp /> : <FaChevronDown />}
                </button>

                {activeIndex === index && (
                  <div className="faq-answer">
                    <p>{faq.answer}</p>
                  </div>
                )}
              </div>
            ))}
          </div>
        </div>
      </section>
    </>
  );
}

export default FAQ;
