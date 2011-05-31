#include <string>

// TODO: Create a binary search to boost performance

// search in the reserved words list if the given word exists
int search_string(const std::string arr[], size_t size, std::string key) {
    for (size_t i = 0; i < size; i++) {
        std::string _word = arr[i]; // get word from the list
        if (key.compare(_word) == 0) // if the word is in the list
            return i;
    }
    
    return -1;
}

int search_int(int arr[], int size, int value) {
    for (int i = 0; i < size; i++) {
        if (value == arr[i])
            return i;
    }
    
    return -1;
}