#include <bits/stdc++.h>
#include <string>
#include <iostream>
#include<stdbool.h>
#include <math.h>

using namespace std;

vector<int> vector_2d[101][27];
vector <string> vector_eps;
char alphabets[27];
int nfaFinals[101];

struct finalTran
{
    int x;
    char c;
    int y;
};
vector<finalTran>vector_dfaTrans;
vector<int>vector_dfaFinalStates;
unordered_set<string> dfaStates;

int charToInt(string &in)
{
    if(in.length()==3)
    {
        return 0;
    }
    else
    {
        char c[2];
        c[0] = in[0];
        return c[0]-'a'+1;
    }
}

char IntToChar(int in)
{
    if(in ==0)
    {
        return 'E';
    }
    else
    {
        char c = (char)(in+96);
        return c;
    }
}

void epsClosure(int element)
{
    queue<int> q;

    int unmarked[100]= {0};
    q.push(element);
    unmarked[element]=1;

    while(!q.empty())
    {
        int x = q.front();
        q.pop();
        //cout << "elements are: " << x << endl;

        for(int j=0; j<vector_2d[x][0].size(); j++)
        {
            int y = vector_2d[x][0][j];
            if(unmarked[y]!=1)
            {
                q.push(y);
                unmarked[y]=1;
                //cout << "pushing element : "<< y << endl;
            }

        }
    }

    string str;
    for (int i: unmarked)
    {
        str.push_back(i + '0');
    }
    vector_eps.push_back(str);
    //cout << str << endl;

}

void nfaTodfa(int noOfalphabet, int n)
{
    queue<string> qs;
    qs.push(vector_eps[0]);


    dfaStates.insert(vector_eps[0]);

    map<string,int>finalMaps;
    int stateCount =0;
    finalMaps[vector_eps[0]]=stateCount++;
    vector <string> vector_final;

    while(!qs.empty())
    {

        string curState = qs.front();
        bool fStates = true;

        for(int i=0; i<curState.size(); i++)
        {
            //cout << i << " : " << curState[i] << ", " << nfaFinals[i] << endl;
            if((curState[i]=='1')&&(nfaFinals[i]==1))
            {
                fStates= false;
                break;
            }
        }
        if(fStates==true)
        {
            vector_dfaFinalStates.push_back(finalMaps[curState]);
        }
        qs.pop();
        // cout << "currentclousre: " << curState << endl;

        for(int i=0; i<noOfalphabet; i++)
        {
            int unmarked[100]= {0};
            //  cout << "alphabet is: " << alphabets[i] << endl;

            for(int l=0; l<n; l++)
            {

                // cout << "state is: " << l << " " << curState[l] << endl;
                if(curState[l]=='1')
                {

                    string s(1, alphabets[i]);
                    int pos = charToInt(s);

                    //cout << "alphabet position is: " << pos << " " << vector_2d[l][pos].size() << endl;
                    for(int state=0; state<vector_2d[l][pos].size(); state++)
                    {
                        int tranStates = vector_2d[l][pos][state];
                        // cout << "states are: " << tranStates << endl;
                        unmarked[tranStates]=1;
                    }
                }
            }

            /*  cout << "for alphabet: " << alphabets[i] << " string is: " <<endl;
              for(int j=0; j<100; j++)
                  cout << unmarked[j] ;

              cout << endl;*/

            int alphabetFinals[100] = {0};
            // cout << "start of epislons: " <<endl;
            for(int epsPos=0; epsPos<n; epsPos++)
            {
                // cout << "state is: " << epsPos << " " << unmarked[epsPos] << endl;
                if(unmarked[epsPos]==1)
                {
                    alphabetFinals[epsPos]=1;
                    string epsClouserPos= vector_eps[epsPos];
                    // cout << "string is: " << epsClouserPos << endl;

                    for(int s=0; s<epsClouserPos.length(); s++)
                    {
                        //cout << "epsilon state is: " << s << " " << epsClouserPos[s] << endl;
                        if(epsClouserPos[s]=='1')
                        {
                            //  cout << "true" <<endl;
                            alphabetFinals[s]=1;
                        }
                    } //end of positional epsilon clousre
                }//condition end
            }//end of final modified string

            string finalStates;
            for (int f: alphabetFinals)
            {
                finalStates.push_back(f + '0');
            }
            // cout << "for alphabet: " << alphabets[i]  << "epsilon is: " << finalStates << endl;
            if (dfaStates.find(finalStates) == dfaStates.end())
            {
                qs.push(finalStates);
                finalMaps[finalStates]=stateCount++;
                dfaStates.insert(finalStates);
                //cout << "Inside the set"<< endl;
            }
            finalTran temp;
            temp.x = finalMaps[curState];
            temp.c = alphabets[i];
            temp.y = finalMaps[finalStates];
            vector_dfaTrans.push_back(temp);

        }


    }
}

int main()
{

    int alphabet[27]= {0};
    int noOfalphabet=0;
    int n,t;
    cin >> n;
    cin >> t;
    //inserting elements
    for(int i=0; i<t; i++)
    {
        int a,c;
        string ss;

        cin >> a >> ss >> c;
        int b = charToInt(ss);

        if(alphabet[b]==0)
        {
            alphabet[b]=1;
            if(ss.length()==1)
            {
                alphabets[noOfalphabet]=ss[0];
                noOfalphabet++;
            }
        }

        vector_2d[a][b].push_back(c);
        //for(int k=0; k<vector_2d[a][b].size(); k++)
        //cout<< vector_2d[a][b][k] << " ";
    }

    int s;
    cin>>s;
    int f;

    for(int i=0; i<s; i++)
    {
        cin >> f;
        nfaFinals[f]=1;
        // cout << alphabet[f] << endl;
    }

    //cout << noOfalphabet << endl;
    for(int i=0; i<n; i++)
    {
        //cout << "for state: " << i << endl;
        epsClosure(i);
    }

    nfaTodfa(noOfalphabet,n);
    //checking alphabets
    /*for(int i=0; i<noOfalphabet; i++)
    {
      cout<< alphabets[i] << endl;
    }*/



    /* while(!q.empty){

         string curState = qs.front();
         qs.pop();

         for(int i=0; i<noOfalphabet; i++){

         }

     }*///////

    /* for(int i=0; i<n; i++)
     {
        // cout << "for state: " << i << " " << vector_eps[i] << endl;
         //epsClosure(i);
     }*/


    cout << dfaStates.size() << endl;
    cout << vector_dfaTrans.size() << endl;

    for(int i=0; i<vector_dfaTrans.size(); i++ )
    {
        cout << vector_dfaTrans[i].x << " "<<vector_dfaTrans[i].c << " " <<vector_dfaTrans[i].y << endl;
    }

    cout << vector_dfaFinalStates.size() << endl;
    for(int i=0; i<vector_dfaFinalStates.size(); i++)
        cout << vector_dfaFinalStates[i] << endl;

    //verifying if it the values are saved properly
    /*for(int i=0; i<n; i++){
        for(int j=0; j<=x; j++){
                cout << " state: " << i << " alphabet: " << IntToChar(j) << endl;
            for(int k=0; k<vector_2d[i][j].size();k++){
                cout <<  " element: " << vector_2d[i][j][k] << " ";
            }
            cout << endl;
        }
    }*/


    return 0;

}
