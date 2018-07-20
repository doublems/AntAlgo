/*읽고 말하기 수열은 다음과 같이 시작하는 수열이다. (Look-and-say sequence라고도 한다)

        1, 11, 21, 1211, 111221, 312211, 13112221, 1113213211, ...
        이 수열은 다음과 같이 앞의 수를 연속된 같은 수의 개수로 묶어서 읽는 방식으로 만들어진다.

        - 1을 "1개의 1"로 읽는다: 11
        - 11을 "2개의 1"로 읽는다: 21
        - 21을 "1개의 2와, 1개의 1"로 읽는다: 1211
        - 1211을 "1개의 1과, 1개의 2와, 2개의 1"로 읽는다: 111221
        - 111221을 "3개의 1과, 2개의 2와, 1개의 1"로 읽는다: 312211
        100번째 수열의 100번째 숫자를 구하라.
        (도전과제 1 : 1000번째 수열의 1000번째 숫자를 구하라.)
        (도전과제 2 : 100만번째 수열의 100만번째 숫자를 구하라.)

        100번째 수열의 100번째 수는 1, 1000번째 수열의 1000번째 수는 3, 100만번째 수열의 100만번째 수는 1이다.*/


import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {

        Main main = new Main();
        System.out.println("END             :"+main.selectNum(5));
    }

    public Queue selectNum(int endNum){
        // 큐에 집어넣어서 순차적으로 확인해야함
        // 순차적으로 활용은 '연속' 이라는 점에 집중해야함
        // 담을 그릇이 필요함 (두개사용하자)

        Queue<Integer> bfAntQueue = new ArrayDeque<Integer>();
        Queue<Integer> afAntQueue = new ArrayDeque<Integer>();

        for(int i = 0;i<endNum;i++){
            if(i==0){
                bfAntQueue.add(1);
                afAntQueue.add(1);
            }else {
                VoAntType voAntType = new VoAntType();
                for(int j = bfAntQueue.size();j>0;j--){
                    int type = bfAntQueue.poll();
                    if(type == voAntType.getType()){
                        voAntType.setAmount(voAntType.getAmount() + 1);
                    }else {
                        if(voAntType.getAmount()!=0){
                            afAntQueue.add(voAntType.getAmount());
                            afAntQueue.add(voAntType.getType());
                        }
                        voAntType.setType(type);
                        voAntType.setAmount(1);
                    }
                }
                afAntQueue.add(voAntType.getAmount());
                afAntQueue.add(voAntType.getType());
            }
            bfAntQueue = afAntQueue;
        }

        return afAntQueue;
    }





    //this class is VO for Ant
    private class VoAntType{
        private int amount = 0;
        private int type = 1;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "VoAntType{" +
                    "amount=" + amount +
                    ", type=" + type +
                    '}';
        }
    }

}
