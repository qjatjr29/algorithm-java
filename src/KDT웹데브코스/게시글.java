package KDT웹데브코스;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class 게시글 {

    static ArrayList<Pair> originalBoard;
    static Stack<String> collection;

    public static class Pair{
        String s, state;
        Pair(String s, String state){
            this.s = s;
            this.state = state;
        }
    }
    public static ArrayList<String> solution(String[] records) {
        ArrayList<String> answer;
        originalBoard =  new ArrayList<Pair>();
        collection = new Stack<>();
        answer = new ArrayList<>();

        int sh = 0;
        int com = 0;
        int curCom = 0;
        for(int i=0;i<records.length;i++)
        {
            if(records[i].equals("check notification"))
            {
                if(curCom == 0)
                {
                    String addString="";
                    int size = originalBoard.size();
                    size --;
                    if(sh == 2)
                    {
                        String temp = originalBoard.get(size).s;
                        int idx = temp.indexOf(" ");
                        String nickname1 =  temp.substring(0,idx);
                        originalBoard.remove(size);
                        size--;
                        temp = originalBoard.get(size).s;
                        idx = temp.indexOf(" ");
                        String nickname2 = temp.substring(0,idx);
                        addString = nickname2+" and " + nickname1 +" shared your post";
                    }
                    else if(sh>=3)
                    {
                        for(int j=1;j<sh;j++)
                        {
                            originalBoard.remove(size);
                            size--;
                        }
                        int people = sh-1;
                        String temp = originalBoard.get(size).s;
                        int idx = temp.indexOf(" ");
                        String nickname =  temp.substring(0,idx);
                        addString = nickname+" and "+String.valueOf(people)+" others shared your post";

                    }
                    else
                    {
                        String temp = originalBoard.get(size).s;
                        int idx = temp.indexOf(" ");
                        String nickname =  temp.substring(0,idx);
                        addString = nickname +" shared your post";
                    }
                    collection.push(addString);
                    originalBoard.remove(size);
                    answer.add(addString);

                    sh = 0;
                    curCom = 1;
                    int cnt = 0;
                    for(int c=size-1;c>=0;c--)
                    {
                        if(originalBoard.get(c).state == "share") break;
                        cnt ++;
                    }
                    com = cnt;
                }
                else
                {
                    String addString="";
                    int size = originalBoard.size();
                    size--;
                    if(com == 2)
                    {
                        String temp = originalBoard.get(size).s;
                        int idx = temp.indexOf(" ");
                        String nickname1 =  temp.substring(0,idx);
                        originalBoard.remove(size);
                        size--;
                        temp = originalBoard.get(size).s;
                        idx = temp.indexOf(" ");
                        String nickname2 = temp.substring(0,idx);
                        addString = nickname2+" and " + nickname1 +" commented on your post";
                    }
                    else if(com>=3)
                    {
                        for(int j=1;j<com;j++)
                        {
                            originalBoard.remove(size);
                            size--;
                        }
                        String temp = originalBoard.get(size).s;
                        int idx = temp.indexOf(" ");
                        String nickname =  temp.substring(0,idx);
                        int people = com-1;
                        addString = nickname+" and "+String.valueOf(people)+" others commented on your post";

                    }
                    else
                    {
                        String temp = originalBoard.get(size).s;
                        int idx = temp.indexOf(" ");
                        String nickname =  temp.substring(0,idx);
                        addString = nickname +" commented on your post";
                    }
                    collection.push(addString);
                    originalBoard.remove(size);
                    answer.add(addString);
                    com=0;
                    curCom=0;
                    int cnt = 0;
                    for(int c=size-1;c>=0;c--)
                    {
                        if(originalBoard.get(c).state == "comment") break;
                        cnt ++;
                    }
                    sh = cnt;
                }
            }

            else
            {
                if(records[i].contains("share"))
                {
                    sh++;
                    originalBoard.add(new Pair(records[i],"share"));
                    curCom = 0;
                    com = 0;
                }
                else if(records[i].contains("comment"))
                {
                    com++;
                    curCom = 1;
                    originalBoard.add(new Pair(records[i],"comment"));
                    sh = 0;
                }
            }
        }
//        answer = new String[collection.size()];
//        System.out.println(collection.size());
//        int index = collection.size()-1;
//        while(true)
//        {
//            if(collection.empty()) break;
//
//            answer[index] = collection.peek();
//            collection.pop();
//            index--;
//        }
        return answer;
    }

    public static void main(String[] args) {

        String[] temp = {
                "john share", "mary share", "jay share", "james comment", "lee share", "check notification", "sally comment", "laura comment", "check notification", "will share", "ruby share", "check notification"
        };
        ArrayList<String> ans = solution(temp);
        for(int i=0;i<ans.size();i++)
        {
            System.out.println(ans.get(i));
        }
    }

}
