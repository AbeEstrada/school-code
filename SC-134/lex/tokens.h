#include <string>
#include <cctype>

void get_tokens(const std::string &data);
void get_tokens(const std::string &data) {
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