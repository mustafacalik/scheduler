package tr.com.app.scheduler.service;

import org.springframework.stereotype.Component;
import tr.com.app.scheduler.model.Presentation;

import java.util.LinkedList;
import java.util.List;

@Component
public class KnapsackScheduleStrategyImpl implements tr.com.app.scheduler.service.ScheduleStrategy {

    @Override
    public List<Presentation> getSubListByMaximumTotalWeight(List<Presentation> list, int totalWeight) {
        int n = list.size();
        int B[][] = new int[n + 1][totalWeight + 1];

        for (int i = 0; i <= n; i++)
            for (int j = 0; j <= totalWeight; j++) {
                B[i][j] = 0;
            }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= totalWeight; j++) {
                B[i][j] = B[i - 1][j];
                int deger = list.get(i - 1).getTimeAsMinute().intValue();
                if ((j >= deger) && (B[i][j] < B[i - 1][j - deger] + deger)) {
                    B[i][j] = B[i - 1][j - deger] + deger;
                }
            }
        }
        List<Presentation> arrList = new LinkedList<>();
        while (n != 0) {
            if (B[n][totalWeight] != B[n - 1][totalWeight]) {
                Presentation etkinlik = list.get(n - 1);
                int deger = etkinlik.getTimeAsMinute().intValue();
                arrList.add(etkinlik);
                totalWeight = totalWeight - deger;
            }
            n--;
        }
        return arrList;
    }
}
