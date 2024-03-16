package racingcar.view;

import static java.text.MessageFormat.format;

import racingcar.util.StringSplitter;
import racingcar.view.io.Input;
import racingcar.view.io.Output;
import racingcar.vo.GameResult;

public class RacingScreen implements RacingView {

    private static final String CAR_NAMES_INPUT_MESSAGE = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String PLAYING_COUNT_INPUT_MESSAGE = "시도할 회수는 몇 회 인가요?";
    private static final String INVALID_INT_FORMAT_MESSAGE = "정수 형태의 숫자만 입력해주세요. [userInput : {0}]";
    private static final String UNEXPECTED_EXCEPTION_MESSAGE = "알 수 없는 오류가 발생했습니다.";
    private static final String DELIMITER = ",";

    private final Input input;
    private final Output output;

    public RacingScreen(final Input input, final Output output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public String[] readCarNames() {
        final String userInput = readUserInput(CAR_NAMES_INPUT_MESSAGE);

        return StringSplitter.split(userInput, DELIMITER);
    }

    @Override
    public int readPlayingCount() {
        final String userInput = readUserInput(PLAYING_COUNT_INPUT_MESSAGE);

        try {
            return Integer.parseInt(userInput);
        } catch (final NumberFormatException e) {
            throw new NumberFormatException(format(INVALID_INT_FORMAT_MESSAGE, userInput));
        }
    }

    private String readUserInput(final String inputMessage) {
        output.printOneLine(inputMessage);

        return input.readOneLine();
    }

    @Override
    public void printGameResult(final GameResult result) {
        output.printOneLine(result.toString());
    }

    @Override
    public void printBusinessExceptionMessage(final String message) {
        output.printOneLine(message);
    }

    @Override
    public void printUnexpectedExceptionMessage() {
        output.printOneLine(UNEXPECTED_EXCEPTION_MESSAGE);
    }
}