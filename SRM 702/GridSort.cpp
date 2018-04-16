#include <cstdio>
#include <cmath>
#include <cstring>
#include <ctime>
#include <iostream>
#include <algorithm>
#include <set>
#include <vector>
#include <sstream>
#include <typeinfo>
#include <fstream>

using namespace std;

class GridSort {
    public:



    int x[2501];
    int y[2501];

    string sort(int n, int m, vector<int> grid) {
        for (int i = 0; i < grid.size(); ++i){
            x[grid[i]] = i / m;
            y[grid[i]] = i % m;
        }
        for (int i = 1; i <= grid.size(); ++i){
            //cout << i << " " << x[i] << " " << y[i] << endl;
        }
        int rightRow = 0;
        for (int i = 1; i <= grid.size(); ++i){
            if ((i - 1) % m == 0){
                rightRow = x[i];
            }else{
                //cout << i << " " << rightRow << endl;
                if (x[i] != rightRow){
                    return "Impossible";
                }
            }
        }
        //cout << "!!!" << endl;
        for (int i = 1; i <= m; ++i){
            int rightColumn = y[i];
            for (int j = i + m; j <= n * m; j += m){
                //cout << j << " " << y[j] << " " << rightColumn << endl;
                if (y[j] != rightColumn){
                    return "Impossible";
                }
            }
        }
        return "Possible";
    }
};

// CUT begin
ifstream data("GridSort.sample");

string next_line() {
    string s;
    getline(data, s);
    return s;
}

template <typename T> void from_stream(T &t) {
    stringstream ss(next_line());
    ss >> t;
}

void from_stream(string &s) {
    s = next_line();
}

template <typename T> void from_stream(vector<T> &ts) {
    int len;
    from_stream(len);
    ts.clear();
    for (int i = 0; i < len; ++i) {
        T t;
        from_stream(t);
        ts.push_back(t);
    }
}

template <typename T>
string to_string(T t) {
    stringstream s;
    s << t;
    return s.str();
}

string to_string(string t) {
    return "\"" + t + "\"";
}

bool do_test(int n, int m, vector<int> grid, string __expected) {
    time_t startClock = clock();
    GridSort *instance = new GridSort();
    string __result = instance->sort(n, m, grid);
    double elapsed = (double)(clock() - startClock) / CLOCKS_PER_SEC;
    delete instance;

    if (__result == __expected) {
        cout << "PASSED!" << " (" << elapsed << " seconds)" << endl;
        return true;
    }
    else {
        cout << "FAILED!" << " (" << elapsed << " seconds)" << endl;
        cout << "           Expected: " << to_string(__expected) << endl;
        cout << "           Received: " << to_string(__result) << endl;
        return false;
    }
}

int run_test(bool mainProcess, const set<int> &case_set, const string command) {
    int cases = 0, passed = 0;
    while (true) {
        if (next_line().find("--") != 0)
            break;
        int n;
        from_stream(n);
        int m;
        from_stream(m);
        vector<int> grid;
        from_stream(grid);
        next_line();
        string __answer;
        from_stream(__answer);

        cases++;
        if (case_set.size() > 0 && case_set.find(cases - 1) == case_set.end())
            continue;

        cout << "  Testcase #" << cases - 1 << " ... ";
        if ( do_test(n, m, grid, __answer)) {
            passed++;
        }
    }
    if (mainProcess) {
        cout << endl << "Passed : " << passed << "/" << cases << " cases" << endl;
        int T = time(NULL) - 1523868162;
        double PT = T / 60.0, TT = 75.0;
        cout << "Time   : " << T / 60 << " minutes " << T % 60 << " secs" << endl;
        cout << "Score  : " << 500 * (0.3 + (0.7 * TT * TT) / (10.0 * PT * PT + TT * TT)) << " points" << endl;
    }
    return 0;
}

int main(int argc, char *argv[]) {
    cout << "hello world" << endl;
    GridSort *grid = new GridSort();
    int n = 3;
    int m = 5;
    vector<int> vec;
    vec.push_back(10);
    vec.push_back(6);
    vec.push_back(8);
    vec.push_back(9);
    vec.push_back(7);

    vec.push_back(5);
    vec.push_back(1);
    vec.push_back(3);
    vec.push_back(4);
    vec.push_back(2);

    vec.push_back(15);
    vec.push_back(11);
    vec.push_back(13);
    vec.push_back(14);
    vec.push_back(12);
    cout << grid->sort(n, m, vec) << endl;
}
// CUT end
