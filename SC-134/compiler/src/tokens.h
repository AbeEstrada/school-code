#include <string>

enum d_types { // DATA TYPES
    D_STRING,
    D_VARIABLE,
    D_RESERVED,
    D_DIGIT,
    D_WORD,
    D_DELIMITER,
    D_OPERATOR
};

std::string keywords[] = { // RESERVED WORDS
    "si",
    "simon",
    "cuando",
    "entonces",
    "wacha"
};
size_t keywords_size = sizeof(keywords) / sizeof(std::string);