import "./PreviousEvents.css";

import birthday1 from "../assets/images/bp1.jpg";
import birthday2 from "../assets/images/bp2.jpg";

import wedding1 from "../assets/images/wp1.jpg";
import wedding2 from "../assets/images/wp2.jpg";

import conference1 from "../assets/images/cp1.jpg";
import conference2 from "../assets/images/cp2.jpg";

import concert1 from "../assets/images/con1.jpg";
import concert2 from "../assets/images/con2.jpg";

import sports1 from "../assets/images/sp1.jpg";
import sports2 from "../assets/images/sp2.jpg";

import collegeFest1 from "../assets/images/cf1.jpg";
import collegeFest2 from "../assets/images/cf2.jpg";

function PreviousEvents() {
  const previousEvents = [
    {
      title: "Royal Wedding Celebration",
      city: "Bengaluru",
      venue: "Royal Grand Convention Hall",
      date: "12 October 2025",
      category: "Wedding Event",
      icon: "💍",
      images: [wedding1, wedding2],
      description:
        "EventHub successfully managed a grand wedding celebration with complete venue coordination, stage decoration, catering and guest management.",
      highlight:
        "The couple and guests appreciated the elegant arrangements, smooth coordination and overall event experience.",
    },

    {
      title: "Grand Birthday Celebration",
      city: "Mysuru",
      venue: "Royal Palace Convention Hall",
      date: "23 November 2025",
      category: "Birthday Event",
      icon: "🎂",
      images: [birthday1, birthday2],
      description:
        "A memorable birthday celebration organized with themed decoration, catering, entertainment and complete guest coordination.",
      highlight:
        "Guests enjoyed the theme, decorations and entertainment, and the celebration received a very positive response from the family.",
    },

    {
      title: "Corporate Leadership Conference",
      city: "Bengaluru",
      venue: "Royal Grand Convention Hall",
      date: "18 January 2026",
      category: "Conference",
      icon: "💼",
      images: [conference1, conference2],
      description:
        "EventHub coordinated a professional corporate conference including stage setup, seating arrangements, audio-visual support and guest management.",
      highlight:
        "The organizers appreciated the professional setup and smooth coordination throughout the conference.",
    },

    {
      title: "Live Music Concert",
      city: "Shivamogga",
      venue: "Green Valley Event Center",
      date: "15 February 2026",
      category: "Concert",
      icon: "🎵",
      images: [concert1, concert2],
      description:
        "A high-energy live music event planned with stage production, sound management, lighting and large audience coordination.",
      highlight:
        "The event attracted a lively crowd and received an enthusiastic response from the audience.",
    },

    {
      title: "Inter-College Sports Event",
      city: "Hassan",
      venue: "Grand Orchid Banquet Hall",
      date: "8 March 2026",
      category: "Sports Event",
      icon: "🏆",
      images: [sports1, sports2],
      description:
        "EventHub coordinated a large sports event with participant management, venue preparation, crowd coordination and event-day support.",
      highlight:
        "Participants and spectators appreciated the organized arrangements and smooth management of the event.",
    },

    {
      title: "College Cultural Fest",
      city: "Bengaluru",
      venue: "EventHub Partner Venue",
      date: "25 April 2026",
      category: "College Fest",
      icon: "🎓",
      images: [collegeFest1, collegeFest2],
      description:
        "A vibrant college cultural fest managed with stage arrangements, sound and lighting, activity coordination and student crowd management.",
      highlight:
        "Students enjoyed the energetic atmosphere and performances, making it one of the most memorable events of the season.",
    },
  ];

  return (
    <div className="previous-events-page">
      <div className="previous-events-container">
        {/* Header */}
        <div className="previous-events-header">
          <span>OUR EXPERIENCE</span>

          <h1>Previous Events</h1>

          <p>
            Take a look at some of the memorable events successfully organized
            and managed by EventHub.
          </p>
        </div>

        {/* Event Cards */}
        <div className="previous-events-grid">
          {previousEvents.map((event, index) => (
            <div className="previous-event-card" key={index}>
              {/* Images */}
              <div className="previous-event-gallery">
                {event.images.map((image, imageIndex) => (
                  <div className="previous-event-image" key={imageIndex}>
                    <img src={image} alt={`${event.title} ${imageIndex + 1}`} />
                  </div>
                ))}
              </div>

              {/* Event Details */}
              <div className="previous-event-content">
                <div className="previous-event-category">
                  {event.icon} {event.category}
                </div>

                <h2>{event.title}</h2>

                <div className="previous-event-info">
                  <p>
                    <strong>📍 City:</strong> {event.city}
                  </p>

                  <p>
                    <strong>🏢 Venue:</strong> {event.venue}
                  </p>

                  <p>
                    <strong>📅 Conducted On:</strong> {event.date}
                  </p>
                </div>

                <p className="previous-event-description">
                  {event.description}
                </p>

                <div className="previous-event-highlight">
                  <span>⭐ Event Highlight</span>
                  <p>{event.highlight}</p>
                </div>

                <div className="previous-event-status">
                  ✓ Successfully Organized
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default PreviousEvents;
