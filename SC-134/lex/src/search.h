#include <string>

// TODO: Create a binary search to boost performance

// search in the reserved words list if the given word exists
int search_string(const std::string array[], size_t size, std::string key);
int search_string(const std::string array[], size_t size, std::string key) {
    int _return = 0;
    
    for(size_t i = 0; i < size; ++i) {
        std::string _word = array[i]; // get word from the list
        if (key.compare(_word) == 0) // if the word is in the list
            _return = 1;
    }
    
    return _return;
}