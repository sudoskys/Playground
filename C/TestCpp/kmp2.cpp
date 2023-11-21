#include <iostream>
#include <string>
#include <vector>

using namespace std;

vector<int> get_next(const string& pattern) {
    int len = pattern.length();
    vector<int> next(len, 0);
    for (int i = 1, j = 0; i < len; ++i) {
        while (j > 0 && pattern[i] != pattern[j]) {
            j = next[j - 1];
        }
        if (pattern[i] == pattern[j]) {
            ++j;
        }
        next[i] = j;
    }
    return next;
}

vector<int> kmp_search(const string& text, const string& pattern) {
    vector<int> positions;
    vector<int> next = get_next(pattern);
    int i = 0, j = 0;
    int n = text.length(), m = pattern.length();
    while (i < n) {
        if (text[i] == pattern[j]) {
            ++i;
            ++j;
        } else {
            if (j > 0) {
                j = next[j - 1];
            } else {
                ++i;
            }
        }
        if (j == m) {
            positions.push_back(i - j);
            j = next[j - 1];
        }
    }
    return positions;
}

int main() {
    string text = "ababcabcacbab";
    string pattern = "abcac";
    vector<int> positions = kmp_search(text, pattern);
    for (int pos: positions){
        cout<<"Found pattern at position "<<pos<<endl;
    }
    return 0;
}
