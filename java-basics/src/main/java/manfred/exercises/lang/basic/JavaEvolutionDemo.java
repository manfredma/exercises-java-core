package manfred.exercises.lang.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 从标准输入读取模块依赖统计数据并按引用量排序输出。
 *
 * 演示了 Stream 的 filter/findAny、自定义 equals 用于列表去重、
 * 以及内部静态类 ModuleStatistics/VersionStatistics 聚合多维统计信息的实战写法。
 */
public class JavaEvolutionDemo {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String tx = br.readLine();
        System.out.println();
        List<ModuleStatistics> moduleAll = new ArrayList<>();
        while (!"Bye".equals(tx)) {
            String[] splits = tx.split("\t");
            String moduleName = splits[1];
            String version = splits[2];
            if (version.contains(".")) {
                version = version.split("\\.")[0] + ".x";
            }
            int usedCount = Integer.parseInt(splits[3]);

            if (!moduleAll.contains(new ModuleStatistics(moduleName))) {
                moduleAll.add(new ModuleStatistics(moduleName));
            }

            ModuleStatistics moduleStatistics =
                    moduleAll.stream().filter(a -> a.moduleName.equals(moduleName)).findAny().get();
            moduleStatistics.usedCount += usedCount;

            String finalVersion = version;
            Optional<VersionStatistics> moduleUsedCount =
                    moduleStatistics.versionStatisticsList.stream().filter(a -> a.version.equals(finalVersion)).findAny();
            if (moduleUsedCount.isPresent()) {
                moduleUsedCount.get().usedCount += usedCount;
                moduleUsedCount.get().versionNum += 1;
            } else {
                VersionStatistics nameCount = new VersionStatistics();
                nameCount.version = finalVersion;
                nameCount.usedCount = usedCount;
                nameCount.versionNum = 1;
                moduleStatistics.versionStatisticsList.add(nameCount);

                moduleStatistics.versionCount += 1;
            }

            tx = br.readLine();
        }

        moduleAll.stream().sorted(Comparator.comparing(ModuleStatistics::getUsedCount).reversed())
                .forEach(a -> {
                    a.versionStatisticsList.sort(Comparator.comparing(VersionStatistics::getUsedCount).reversed());
                    System.out.println(a);
                    // System.out.println("=====================");
                });

    }

    static class ModuleStatistics {
        String moduleName;

        Integer usedCount = 0;

        Integer versionCount = 0;

        List<VersionStatistics> versionStatisticsList = new ArrayList<>();

        public ModuleStatistics(String moduleName) {
            this.moduleName = moduleName;
        }

        public Integer getUsedCount() {
            return usedCount;
        }

        @Override
        public boolean equals(Object obj) {
            ModuleStatistics other = (ModuleStatistics) obj;
            return this.moduleName.equals(other.moduleName);
        }

        @Override
        public String toString() {
            String result = "";
            result += moduleName + "\n(引用量：" + usedCount + ") \n";
            for (VersionStatistics versionStatistics : versionStatisticsList) {
                result += String.format("%-12s", versionStatistics.version) + " | 子版本数量： " + String.format("%-3d", versionStatistics.versionNum) +
                        " | 引用数量：";
                result += String.format("%-3d", versionStatistics.usedCount);
                result += "\n";
            }
            return result;
        }
    }

    static class VersionStatistics {
        String version;
        int usedCount;

        int versionNum;

        @Override
        public String toString() {
            return version + "(子版本数量：" + versionNum + ")\t" + usedCount;
        }

        public int getUsedCount() {
            return usedCount;
        }
    }

}
