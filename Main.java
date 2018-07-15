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
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        System.out.println("END"+selectNum(10));
    }

    public static int selectNum(int endNum){
        // 큐에 집어넣어서 순차적으로 확인해야함
        // 순차적으로 활용은 '연속' 이라는 점에 집중해야함
        // 담을 그릇이 필요함 (두개사용하자)

        Queue<Integer> bfAntQueue = new ArrayDeque<Integer>();
        Queue<Integer> afAntQueue = new ArrayDeque<Integer>();

        for(int i = 0; i<endNum; i++){
            //첫 시작일경우 1을 큐에 추가한다.
            if(i == 0){
                bfAntQueue.add(1);
            }

            VoAntType voAntType = new VoAntType();


            //작업대상 큐가 비어질 때 까지 동작 또는 사이즈가 endNum보다 작을때까지
            while (!bfAntQueue.isEmpty() || !(bfAntQueue.size()<=endNum)){
                
                //꺼내기
                int eachPreData = bfAntQueue.poll();
                //다른 숫자가 나타나면 저장 후 voAntType 업데이트
                //System.out.println("eachPreData"+eachPreData);
                //System.out.println("voAntType.getType()"+voAntType.getType());
                //System.out.println(bfAntQueue.size());

                if(eachPreData != voAntType.getType()){
                    //저장
                    afAntQueue.add(voAntType.getAmount());
                    afAntQueue.add(voAntType.getType());
                    //voAntType 업데이트
                    voAntType.setType(eachPreData);
                    voAntType.setAmount(0);
                }else{
                    //같은 숫자인 경우 수량 증가
                    voAntType.setAmount((voAntType.getAmount())+1);
                    //System.out.println(voAntType.getAmount());
                    if(bfAntQueue.size()==0){
                        System.out.println(voAntType.getAmount());
                        System.out.println(voAntType.getType());
                        //저장
                        afAntQueue.add(voAntType.getAmount());
                        afAntQueue.add(voAntType.getType());
                    }
                }
            }
            System.out.println("i:"+i);
            //작업 후의 큐를 작업 전 큐에 반영
            //bfAntQueue = afAntQueue;
        }

        return afAntQueue.size();
    }

    //this class is VO for Ant
    private static class VoAntType{
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
