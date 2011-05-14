/*
    Orignal code
    http://cboard.cprogramming.com/cplusplus-programming/129819-cplusplus-parser-simplified-lexical-analyzer.html#post968919
*/

#include <iostream>
#include <string>
#include <cctype>

void print_tokens(const std::string &data);
int search_string(const std::string array[], size_t size, std::string key);

std::string keywords[] = {
    "void",
    "function",
    "cout",
    "return"
};
size_t keywords_size = sizeof(keywords) / sizeof(std::string);

int main() {
    std::string line;
    
    while(std::getline(std::cin, line)) {
        print_tokens(line);
    }
    
    return 0;
}

void print_tokens(const std::string &data) {
    enum {
        MODE_OUTSIDE,
        MODE_IN_WORD,
        MODE_SYMBOL
    } mode = MODE_OUTSIDE;
    
    std::string word;
    std::string::size_type x = 0;
    
    while(x < data.length()) {
        char c = data[x];
        
        switch(mode) {
            case MODE_OUTSIDE:
                if (std::isspace(c)) {
                    x++;
                    
                } else if (std::isalnum(c)) {
                    mode = MODE_IN_WORD;
                    
                } else {
                    mode = MODE_SYMBOL;
                }
                break;
                
            case MODE_IN_WORD:
                if (std::isalnum(c)) {
                    word += c;
                    x++;
                    
                } else {
                    if (search_string(keywords, keywords_size, word)) {
                        std::cout << "yes: " << word << std::endl;
                    } else {
                        std::cout << "no: " << word << std::endl;
                    }
                    word.erase();
                    mode = MODE_OUTSIDE;
                }
                break;
                
            case MODE_SYMBOL:
                if (!std::isalnum(c) && !std::isspace(c)) {
                    std::cout << "[" << c << "] " << std::endl;
                    x++;
                    
                } else {
                    mode = MODE_OUTSIDE;
                }
                break;
        }
    }
}

int search_string(const std::string array[], size_t size, std::string key) {
    int _return = 0;
    
    for(size_t i = 0; i < size; ++i) {
        if (key.compare(array[i]) == 0)
            _return = 1;
    }
    
    return _return;
}