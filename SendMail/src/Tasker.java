import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Tasker {
    public static void main(String[] args) throws Exception {
        Thread.sleep((long) ((double)Math.random() * 1000));
        System.out.println("Запускаем задачи по рассылке...");
        System.out.println();
        Thread.sleep((long) ((double)Math.random() * 1000));
        Task();

    }


    public static void Task() {

        ExecutorService executor = Executors.newWorkStealingPool();


        List<SendMailTask> sendMailTasks = Arrays.asList(
                new SendMailTask(new Mail("job mail")),
                new SendMailTask(new Mail("advertising mail")),
                new SendMailTask(new Mail("mail")));

        try {
            executor.invokeAll(sendMailTasks)
                    .stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception e) {
                            throw new IllegalStateException(e);
                        }
                    })
                    .forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class Mail {
        String content;

        public Mail(String content) {
            this.content = content;
        }
    }

    private static class MailSendResult {
    }

    private static class SendMailTask implements Callable<MailSendResult> {
        private Mail mail;

        public SendMailTask(Mail mail) {
            this.mail = mail;
        }

        @Override
        public MailSendResult call() throws Exception {
            System.out.println(mail.content);
            return new MailSendResult();
        }
    }
}
