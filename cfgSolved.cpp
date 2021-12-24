#include <bits/stdc++.h>
#include <string>

using namespace std;

int main()
{

    string rule[205] = { "" };
    string production[205] = { "" };

    int rules;
    cin >> rules;
    string r1,r2;

    for(int i=0; i<rules; i++)
    {
        cin >> r1 >> r2;
        rule[i]=r1;
        production[i]=r2;

        //cout << rule[i] << " " << production[i] << endl;
    }

    int table[35][35][26];

    int in;
    cin>> in;
    string inputs;

    for(int input=0; input<in; input++)
    {
        cin >> inputs;
        memset(table,0,sizeof(table));

        for(int i=0; i<inputs.size(); i++)
        {

            for(int r=0; r<rules; r++)
            {
                if(production[r].size()==1)
                {
                    if(production[r][0]==inputs[i])
                    {
                        table[0][i][rule[r][0]-'A']=1;
                    }
                }
            }
        }//1st row done;

        for(int i=1; i<inputs.size(); i++)
        {
            for(int j=0; j<inputs.size()-i; j++)
            {
               // cout << "Index = " << i << "  " << j << endl;
                for(int k=0; k<i; k++)
                {
                    int row_x=k;
                    int row_y=j;
                    int col_x=i-k-1;
                    int col_y=j+k+1;

                   // cout << row_x << "  " << row_y << "  " << col_x << "  " << col_y << endl;

                    for(int r=0; r<rules; r++)
                    {
                        if(production[r].size()==2)
                        {
                            int x,y;
                            x=production[r][0]-'A';
                            y=production[r][1]-'A';

                            if(table[row_x][row_y][x]==1 && table[col_x][col_y][y]==1)
                            {
                                table[i][j][rule[r][0]-'A']=1;
                            }
                        }
                    }
                }
               // cout << "==========================\n";
            }
        }//table done

        if(table[inputs.size()-1][0][rule[0][0]-'A']==1)
            cout<< "yes" << endl;
        else
           cout << "no" << endl;

    }



    return 0;
}
