#include <valarray>
#include "Sector.h"

// Constructor implementation

Sector::Sector(int x, int y, int z) : x(x), y(y), z(z), left(nullptr), right(nullptr), parent(nullptr), color(RED) {
        // TODO: Calculate the distance to the Earth, and generate the sector code
    // Calculate the distance to the Earth using Euclidean distance formula
    distance_from_earth = std::sqrt(static_cast<double>(x*x + y*y + z*z));
    color= false;
    // Generate the sector code based on the specified logic
    // DistanceComponent: Truncate the distance to an integer (floor operation)
    int truncated_distance = static_cast<int>(distance_from_earth);

    // CoordinateComponents:
    // For each coordinate, append the respective directional letter
    // 'S' for Same, 'R' for Right(X), 'U' for Up(Y), 'F' for Forward(Z)
    sector_code += std::to_string(truncated_distance);
    if (x == 0) {
        sector_code += "S";
    } else if (x > 0) {
        sector_code += "R";
    } else {
        sector_code += "L";
    }

    if (y == 0) {
        sector_code += "S";
    } else if (y > 0) {
        sector_code += "U";
    } else {
        sector_code += "D";
    }

    if (z == 0) {
        sector_code += "S";
    } else if (z > 0) {
        sector_code += "F";
    } else {
        sector_code += "B";
    }

    // Append the truncated distance to the sector code
}
int distance_earth(int x,int y , int z){
    return std::sqrt(static_cast<double>(x*x + y*y + z*z));
}
Sector::~Sector() {
    // TODO: Free any dynamically allocated memory if necessary
}

Sector& Sector::operator=(const Sector& other) {
    // TODO: Overload the assignment operator
    if (this != &other) {
        x = other.x;
        y = other.y;
        z = other.z;
        distance_from_earth = other.distance_from_earth;
        sector_code = other.sector_code;
        left = other.left;
        right = other.right;
        parent = other.parent;
        color = other.color;
    }
    return *this;
}

bool Sector::operator==(const Sector& other) const {
    return (x == other.x && y == other.y && z == other.z);
}

bool Sector::operator!=(const Sector& other) const {
    return !(*this == other);
}