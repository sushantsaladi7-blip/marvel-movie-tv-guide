# Marvel Movie & TV Timeline Guide

A Java-based GUI application that organizes MCU and Non-MCU movies and TV shows into viewing lists, including a guide to important titles leading up to *Avengers: Doomsday* and *Avengers: Secret Wars*.

## The Problem

Marvel has released numerous movies and TV shows across the MCU and other Marvel universes. For fans who stopped following the MCU after *Avengers: Endgame*, it can be difficult to understand which titles they should watch to catch up before the upcoming Avengers movies.

Additionally, fans who want to explore Marvel media beyond the MCU may find it difficult to navigate titles across different universes and timelines.

This project aims to organize Marvel media into structured viewing lists so users can easily explore titles and identify important ones to watch.

## How It Works

The application provides a graphical user interface with four main categories:

1. **MCU Movies, TV Shows & Special Presentations in Release Order:** Browse MCU media according to its original release order.
2. **All MCU Media in Chronological Order:** Explore MCU movies, TV shows, and Special Presentations according to the chronological order of their stories.
3. **Non-MCU Media:** Explore Marvel movies and TV shows outside the MCU, organized into relevant sections.
4. **Road to Secret Wars:** Follow a chronological viewing guide featuring important MCU and Non-MCU titles to watch before *Avengers: Doomsday* and *Avengers: Secret Wars*.

The application uses CSV files to store Marvel media information and track watched status. The TMDB API provides additional information, including movie and TV show posters, ratings, and overviews.

![System Architecture](docs/diagram.png)

**Eisenhower Matrix:** [View Eisenhower Matrix](docs/eisenhower-matrix.pdf)

## Tech Stack

- **Programming Language:** Java
- **GUI Framework:** JavaFX
- **Java Libraries and APIs:**
  - Java Collections (ArrayList)
  - Comparator
  - Java HTTP Client
  - File I/O (BufferedReader, PrintWriter, etc.)
- **External Library:** Gson
- **Build Tool:** Maven
- **External API:** The Movie Database (TMDB) API
- **Data Storage:** CSV files
- **Development Tools:** Visual Studio Code, Git, GitHub

## Setup

### Prerequisites

- Java Development Kit (JDK)
- Apache Maven
- Visual Studio Code (recommended)
- Git
- A TMDB API Read Access Token

### Installation

Clone the repository and navigate to the project directory:

```bash
git clone https://github.com/sushantsaladi7-blip/marvel-movie-tv-guide.git
cd marvel-movie-tv-guide
```

### TMDB API Configuration

The application requires a TMDB API Read Access Token to retrieve media information.

1. Create a TMDB account and obtain an API Read Access Token from your TMDB account settings.
2. Set the token as an environment variable in your terminal:

```bash
export TMDB_TOKEN='YOUR_TMDB_READ_ACCESS_TOKEN'
```

3. Keep the token private. Do not commit API keys or access tokens to the GitHub repository.

### Running the Application

Run the following command from the project directory:

```bash
mvn clean javafx:run
```

## Usage

When the application starts, users are greeted with a home screen containing four viewing categories:

1. **MCU Release Order**
2. **MCU Chronology Order**
3. **Non-MCU Release Order**
4. **Road to Avengers: Secret Wars**

Select a category to browse its corresponding list of Marvel movies and TV shows.

Each title is displayed with its corresponding poster and information. Titles marked as important for the upcoming Avengers movies are highlighted in the relevant lists.

Users can also:

- Mark movies and TV shows as **Watched** or **Not Watched** using checkboxes.
- Track their viewing progress across the different categories.
- Have their watched status saved to the CSV file, allowing it to persist after closing and reopening the application.
- Navigate back to the home screen to explore another category.

Media posters and additional information are retrieved using the TMDB API.

## Roadmap

- [x] Create Java classes to represent Marvel movies and TV shows.
- [x] Load Marvel media data from CSV files.
- [x] Implement MCU media lists in release order and chronological order.
- [x] Implement Non-MCU media lists.
- [x] Implement the Road to Secret Wars viewing guide.
- [x] Build the JavaFX graphical user interface.
- [x] Integrate the TMDB API to retrieve media information and posters.
- [x] Implement watched status tracking and CSV persistence.
- [x] Improve the graphical interface and overall user experience.
- [x] Test the application, fix bugs, and finalize the interface.

## Author

**Sushant Saladi**

**GitHub:** [sushantsaladi7-blip](https://github.com/sushantsaladi7-blip)
