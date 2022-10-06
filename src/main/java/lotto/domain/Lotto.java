package lotto.domain;

import java.util.List;

import static java.util.stream.Collectors.reducing;

/**
 * Created by seungwoo.song on 2022-10-06
 */
public class Lotto {

    public static final int VALID_COUNT = 6;
    private final List<Integer> lottoNumbers;

    public Lotto(List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public static Lotto of(List<Integer> lottoNumbers) {
        if (lottoNumbers == null || lottoNumbers.isEmpty() || lottoNumbers.size() != VALID_COUNT) {
            throw new IllegalArgumentException("로또번호 갯수가 유효하지 않습니다.");
        }
        return new Lotto(lottoNumbers);
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }

    public LottoResult getResult(Lotto winLottoNumber) {
        return LottoResult.from(matchCount(winLottoNumber));
    }

    private int matchCount (Lotto winLottoNumber) {
        return winLottoNumber.getLottoNumbers()
                .stream()
                .filter(lottoNumbers::contains)
                .collect(reducing(0, e -> 1, Integer::sum));
    }
}