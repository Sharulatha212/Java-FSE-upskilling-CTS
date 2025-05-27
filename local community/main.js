// Event class
class Event {
  constructor(id, name, category, date, seats) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.date = new Date(date);
    this.seats = seats;
  }
  register() {
    if (this.seats > 0) {
      this.seats--;
      return true;
    }
    return false;
  }
  cancel() {
    this.seats++;
  }
}

// Initial event data
const events = [
  new Event(1, "Workshop on Baking", "Cooking", "2025-06-10", 15),
  new Event(2, "Jazz Night", "Music", "2025-07-05", 30),
  new Event(3, "Rock Concert", "Music", "2025-08-20", 0), // Full event
  new Event(4, "Yoga Workshop", "Health", "2025-09-01", 20),
  new Event(5, "Art Workshop", "Art", "2025-07-15", 10),
  new Event(6, "Tech Talk", "Technology", "2025-08-20", 25),
];

const container = document.querySelector("#eventsContainer");
const categoryFilter = document.querySelector("#categoryFilter");
const searchInput = document.querySelector("#searchInput");
const eventSelect = document.querySelector("#eventSelect");
const spinner = document.querySelector("#spinner");

// Render event cards
function renderEvents() {
  container.innerHTML = "";
  spinner.style.display = "block";

  setTimeout(() => {
    spinner.style.display = "none";

    const filterCategory = categoryFilter.value.toLowerCase();
    const searchTerm = searchInput.value.toLowerCase();

    // Filter and search logic
    const filteredEvents = events.filter((event) => {
      const matchCategory =
        filterCategory === "all" || event.category.toLowerCase() === filterCategory;
      const matchName = event.name.toLowerCase().includes(searchTerm);
      const futureEvent = event.date >= new Date(); // only upcoming events
      return matchCategory && matchName && futureEvent;
    });

    if (filteredEvents.length === 0) {
      container.textContent = "No events match your criteria.";
      return;
    }

    filteredEvents.forEach((event) => {
      const card = document.createElement("div");
      card.className = "event-card";

      const name = document.createElement("div");
      name.className = "event-name";
      name.textContent = event.name;

      const category = document.createElement("p");
      category.textContent = `Category: ${event.category}`;

      const date = document.createElement("p");
      date.textContent = `Date: ${event.date.toDateString()}`;

      const seats = document.createElement("p");
      seats.textContent = `Available Seats: ${event.seats}`;

      const registerBtn = document.createElement("button");
      registerBtn.textContent = "Register";
      registerBtn.disabled = event.seats === 0;

      registerBtn.addEventListener("click", () => {
        if (event.register()) {
          alert(`Registered for ${event.name}`);
          renderEvents(); // Update UI after registration
          populateEventSelect(); // Update dropdown seat info
        } else {
          alert("Sorry, no seats available.");
        }
      });

      card.appendChild(name);
      card.appendChild(category);
      card.appendChild(date);
      card.appendChild(seats);
      card.appendChild(registerBtn);

      container.appendChild(card);
    });
  }, 300);
}

// Populate event dropdown in registration form with only upcoming events and seats > 0
function populateEventSelect() {
  eventSelect.innerHTML = '<option value="">-- Select an Event --</option>';

  events.forEach((event) => {
    if (event.date >= new Date() && event.seats > 0) {
      const option = document.createElement("option");
      option.value = event.id;
      option.textContent = `${event.name} (${event.seats} seats left)`;
      eventSelect.appendChild(option);
    }
  });
}

// Form validation and submission
const form = document.getElementById("registrationForm");
const responseMessage = document.getElementById("responseMessage");

form.addEventListener("submit", function (e) {
  e.preventDefault();

  // Clear previous errors and messages
  document.querySelectorAll(".error").forEach((el) => (el.textContent = ""));
  responseMessage.textContent = "";
  responseMessage.style.color = "black";

  const { name, email, event: selectedEvent } = form.elements;

  let hasError = false;

  // Validate name
  if (!name.value.trim()) {
    document.getElementById("nameError").textContent = "Name is required.";
    hasError = true;
  }

  // Validate email format
  if (!email.value.trim()) {
    document.getElementById("emailError").textContent = "Email is required.";
    hasError = true;
  } else if (!/\S+@\S+\.\S+/.test(email.value)) {
    document.getElementById("emailError").textContent = "Email format is invalid.";
    hasError = true;
  }

  // Validate event selection
  if (!selectedEvent.value) {
    document.getElementById("eventError").textContent = "Please select an event.";
    hasError = true;
  }

  if (hasError) return;

  // Find the selected event
  const eventObj = events.find((ev) => ev.id == selectedEvent.value);

  if (!eventObj) {
    responseMessage.style.color = "red";
    responseMessage.textContent = "Selected event not found.";
    return;
  }

  if (eventObj.seats === 0) {
    responseMessage.style.color = "red";
    responseMessage.textContent = "Sorry, no seats available for selected event.";
    populateEventSelect();
    renderEvents();
    return;
  }

  // Register user for the event (decrement seats)
  eventObj.register();

  responseMessage.style.color = "green";
  responseMessage.textContent = `Thanks for registering, ${name.value}! You signed up for ${eventObj.name}.`;

  // Reset form and update UI
  form.reset();
  populateEventSelect();
  renderEvents();
});

// Debounce function for search input
function debounce(fn, delay) {
  let timeoutId;
  return function (...args) {
    clearTimeout(timeoutId);
    timeoutId = setTimeout(() => {
      fn.apply(this, args);
    }, delay);
  };
}

// Event listeners for filters
categoryFilter.addEventListener("change", renderEvents);
searchInput.addEventListener("input", debounce(renderEvents, 300));

// Initial population
populateEventSelect();
renderEvents();
