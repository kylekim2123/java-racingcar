package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static racingcar.TestRacingCarConfig.basicRule;
import static racingcar.TestRacingCarConfig.moveForwardNumberGenerator;
import static racingcar.config.RacingCarException.PLAYING_COUNT_OUT_OF_RANGE;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import racingcar.domain.MovementStrategy;
import racingcar.vo.GameResult;

class RaceTest {

    private static MovementStrategy movementForwardStrategy;

    @BeforeAll
    static void setUp() {
        movementForwardStrategy = new MovementStrategy(basicRule(), moveForwardNumberGenerator());
    }

    @Test
    @DisplayName("자동차 이름 목록을 통해 레이스를 생성한다.")
    void of_CarNames_Race() {
        final String[] carNames = {"kyle", "alex", "haley"};

        assertThat(Race.of(carNames, movementForwardStrategy))
                .isNotNull();
    }

    @Test
    @DisplayName("자동차 경주를 진행하면 그에 따른 경주 결과를 반환한다.")
    void progress_GameResult_WinnerNames() {
        final String[] carNames = {"kyle", "alex", "haley"};
        final int playingCount = 1;

        final Race race = Race.of(carNames, movementForwardStrategy);
        final GameResult result = race.progress(playingCount);

        assertThat(result.winnerNames())
                .containsOnly(carNames);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    @DisplayName("0 이하의 레이싱 시도 횟수만큼 경주를 하려는 경우 예외를 던진다.")
    void progress_NegativeOrZeroPlayingCount_Exception(final int negativeOrZeroPlayingCount) {
        final String[] carNames = {"kyle", "alex", "haley"};
        final Race race = Race.of(carNames, movementForwardStrategy);

        assertThatThrownBy(() -> race.progress(negativeOrZeroPlayingCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PLAYING_COUNT_OUT_OF_RANGE.message(negativeOrZeroPlayingCount));
    }
}
