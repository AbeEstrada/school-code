#include <string>
#include <cctype>

void tokens(const std::string &data);
void tokens(const std::string &data) {
    enum {
        MODE_OUTSIDE,
        MODE_IN_STRING,
        MODE_IN_VAR,
        MODE_IN_WORD,
        MODE_SYMBOL
    } mode = MODE_OUTSIDE;
    
    int is_string = 0;
    std::string string;
    std::string var;
    std::string word;
    std::string::size_type x = 0;
    
    while(x < data.length()) {
        char c = data[x];
        
        switch(mode) {
            case MODE_OUTSIDE:
                if (std::isspace(c) || std::iscntrl(c)) {
                    x++;
                    
                } else if (c == '\"') {
                    mode = MODE_IN_STRING;
                    x++;
                    
                } else if (c == '@') {
                    mode = MODE_IN_VAR;
                    x++;
                            
                } else if (std::isalnum(c) || c == '_') {
                    mode = MODE_IN_WORD;
                
                } else {
                    mode = MODE_SYMBOL;
                }
                break;
                
            case MODE_IN_STRING:
                if (c == '\"') {
                    std::cout << "string: " << string << std::endl;
                    string.erase();
                    mode = MODE_OUTSIDE;
                    
                } else {
                    string += c;
                }
                x++;
                break;
            
            case MODE_IN_VAR:
                if (std::isalnum(c) || c == '@') {
                    var += c;
                    x++;
                    
                } else {
                    std::cout << "variable: @" << var << std::endl;
                    var.erase();
                    mode = MODE_OUTSIDE;
                }
                break;
                
            case MODE_IN_WORD:
                if (std::isalnum(c) || c == '_') {
                    word += c;
                    x++;
                    
                } else {
                    if (search_string(keywords, keywords_size, word)) {
                        std::cout << "reserved: " << word << std::endl;
                        /* Homework
                        if (word.compare("while") == 0) {
                            _while_counter++;
                        } else if (word.compare("if") == 0) {
                            _if_counter++;
                        }*/
                        
                    } else {
                        int number = atoi(word.c_str());
                        
                        if (number) {
                            std::cout << "digit: " << number << std::endl;
                            
                        } else {
                            std::cout << "word: " << word << std::endl;
                            /* Homework
                            _identifiers_counter++; */
                        }
                    }
                    word.erase();
                    mode = MODE_OUTSIDE;
                }
                break;
                
            case MODE_SYMBOL:
                if (!std::isalnum(c) && !std::isspace(c)) {
                    if (c == ';') {
                        std::cout << "end of line" << c << std::endl;
                        
                    } else {
                        std::cout << "[" << c << "] " << std::endl;
                        /* Homework
                        if (c == '=') {
                            _equal_counter++;
                        } */
                    }
                    x++;
                    
                } else {
                    mode = MODE_OUTSIDE;
                }
                break;
        }
    }
}