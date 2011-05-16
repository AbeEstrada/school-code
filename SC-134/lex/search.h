#include <string>

// TODO: Create a binary search to boost performance

int search_string(const std::string array[], size_t size, std::string key);
int search_string(const std::string array[], size_t size, std::string key) {
    int _return = 0;
    
    for(size_t i = 0; i < size; ++i) {
        if (key.compare(array[i]) == 0)
            _return = 1;
    }
    
    return _return;
}