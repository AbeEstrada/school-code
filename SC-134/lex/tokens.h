#include <string>
#include <cctype>

void tokens(const std::string &data);
void tokens(const std::string &data) {
    enum {
        MODE_OUTSIDE,
        MODE_IN_STRING,
        MODE_IN_WORD,
        MODE_SYMBOL
    } mode = MODE_OUTSIDE;
    
    int is_string = 0;
    std::string string;
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
                
            case MODE_IN_STRING:
                if (std::isalnum(c) || std::isspace(c)) {
                    string += c;
                    
                } else if (c == '\"') {
                    std::cout << "string: " << string << std::endl;
                    string.erase();
                    mode = MODE_OUTSIDE;
                }
                x++;
                break;
            
            case MODE_IN_WORD:
                if (std::isalnum(c)) {
                    word += c;
                    x++;
                    
                } else {
                    if (search_string(keywords, keywords_size, word)) {
                        std::cout << "reserved: " << word << std::endl;
                        
                    } else if (is_string == 0) {
                        std::cout << "word: " << word << std::endl;
                    }
                    word.erase();
                    mode = MODE_OUTSIDE;
                }
                break;
                
            case MODE_SYMBOL:
                if (!std::isalnum(c) && !std::isspace(c)) {
                    if (c == '\"') {
                        mode = MODE_IN_STRING;
                        
                    } else if (c == ';') {
                        std::cout << "[" << c << "] end of line" << std::endl;
                        
                    } else {
                        std::cout << "[" << c << "] " << std::endl;
                    }
                    x++;
                    
                } else {
                    mode = MODE_OUTSIDE;
                }
                break;
        }
    }
}