#include<bits/stdc++.h>
#include<string.h>
#include <iostream>

using namespace std;

 int charToInt(String in) {

    char c = in.charAt(0);
    return c - 'a' + 1;
    }
int main()
{
    int n;
    cin>> n;
    vector<string> literal_vec;

    for(int i=0; i<n; i++){
        string s1, s2, s3 =" ";
        cin >> s1 >> s2 >> s3;

        literal_vec.push_back(s1);
        literal_vec.push_back(s2);
        literal_vec.push_back(s3);
    }
    for(int i = 0; i<literal_vec.size(); i++) {
      cout << literal_vec[i] << endl;
   }

}
