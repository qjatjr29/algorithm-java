package baekJoon.dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class 줄서기 {

    private static final int ONE_LINE_PEOPLE = 5;
    private static final String CAN_ENTER = "GOOD";
    private static final String CAN_NOT_ENTER = "BAD";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int line = Integer.parseInt(input.nextToken());
        List<Ticket> ticketList = new ArrayList<>();
        Ticket[][] tickets = new Ticket[line][ONE_LINE_PEOPLE];
        Stack<Ticket> waitingLine = new Stack<>();
        String answer = "";

        for(int i = 0; i < line; i++) {
            input = new StringTokenizer(br.readLine());
            for(int j = 0; j < ONE_LINE_PEOPLE; j++) {
                String ticketInfo = input.nextToken();
                String[] splitTicket = ticketInfo.split("-");
                Ticket ticket = new Ticket(splitTicket[0].charAt(0),
                    Integer.parseInt(splitTicket[1]));
                ticketList.add(ticket);
                tickets[i][j] = ticket;
            }
        }

        Collections.sort(ticketList);
        int enterTicketNumber = 0;

        for(int i = 0; i < line; i++) {
            for(int j = 0; j < ONE_LINE_PEOPLE; j++) {
                Ticket ticketToEnter = ticketList.get(enterTicketNumber);
                Ticket currentTicket = tickets[i][j];
                if(ticketToEnter.alpha.equals(currentTicket.alpha)
                    && ticketToEnter.number == currentTicket.number) {
                    enterTicketNumber++;
                    continue;
                }
                if(!waitingLine.isEmpty()) {
                    Ticket waitingTicket = waitingLine.peek();
                    if(waitingTicket.alpha.equals(ticketToEnter.alpha)
                        && waitingTicket.number == ticketToEnter.number) {
                        waitingLine.pop();
                        enterTicketNumber++;
                        j--;
                        continue;
                    }
                }
                waitingLine.add(currentTicket);
            }
        }
        if(!waitingLine.isEmpty()) {
            while(true) {
                if(enterTicketNumber >= ticketList.size()) break;
                Ticket ticketToEnter = ticketList.get(enterTicketNumber);
                Ticket waitingTicket = waitingLine.peek();
                if (ticketToEnter.alpha.equals(waitingTicket.alpha)
                    && ticketToEnter.number == waitingTicket.number) {
                    waitingLine.pop();
                    enterTicketNumber++;
                } else {
                    answer = CAN_NOT_ENTER;
                    break;
                }
            }
            if(!answer.equals(CAN_NOT_ENTER)) answer = CAN_ENTER;
        }
        else answer = CAN_ENTER;
        bw.write(answer);
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static class Ticket implements Comparable<Ticket> {

        Character alpha;
        int number;

        Ticket(Character alpha, int number) {
            this.alpha = alpha;
            this.number = number;
        }

        @Override
        public int compareTo(Ticket o) {
            if(this.alpha.equals(o.alpha)) return this.number - o.number;
            return this.alpha - o.alpha;
        }
    }

}

