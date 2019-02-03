package misc;

/**
 * @author Samuel Nuttall
 */
public class WorkSchedule {
    private WorkSchedule.Hour[] hours;

    public WorkSchedule(int var1) {
        this.hours = new WorkSchedule.Hour[var1];

        for(int var2 = 0; var2 < var1; ++var2) {
            this.hours[var2] = new WorkSchedule.Hour();
            this.hours[var2].requiredNumber = 0;
            this.hours[var2].workingEmployees = new String[0];
        }

    }

    public WorkSchedule.Hour readSchedule(int var1) {
        return this.hours[var1];
    }

    public void setRequiredNumber(int var1, int var2, int var3) {
        for(int var4 = var2; var4 <= var3; ++var4) {
            this.hours[var4].requiredNumber = var1;
        }

    }

    public boolean addWorkingPeriod(String var1, int var2, int var3) {
        if (var2 >= 0 && var3 < this.hours.length) {
            int var4;
            for(var4 = var2; var4 <= var3; ++var4) {
                for(int var5 = 0; var5 < this.hours[var4].workingEmployees.length; ++var5) {
                    if (this.hours[var4].workingEmployees[var5].equals(var1)) {
                        return false;
                    }
                }

                if (this.hours[var4].requiredNumber == this.hours[var4].workingEmployees.length) {
                    return false;
                }
            }

            for(var4 = var2; var4 <= var3; ++var4) {
                String[] var7 = new String[this.hours[var4].workingEmployees.length + 1];

                for(int var6 = 0; var6 < this.hours[var4].workingEmployees.length; ++var6) {
                    var7[var6] = this.hours[var4].workingEmployees[var6];
                }

                var7[this.hours[var4].workingEmployees.length] = var1;
                this.hours[var4].workingEmployees = var7;
            }

            return true;
        } else {
            return false;
        }
    }

    public String[] workingEmployees(int var1, int var2) {
        String[] var3 = new String[this.hours[var1].workingEmployees.length + this.hours[var2].workingEmployees.length];
        int var4 = 0;

        int var5;
        int var6;
        for(var5 = 0; var5 < this.hours[var1].workingEmployees.length; ++var5) {
            for(var6 = 0; var6 < var4 && !var3[var6].equals(this.hours[var1].workingEmployees[var5]); ++var6) {
            }

            if (var6 == var4) {
                var3[var4++] = this.hours[var1].workingEmployees[var5];
            }
        }

        for(var5 = 0; var5 < this.hours[var2].workingEmployees.length; ++var5) {
            for(var6 = 0; var6 < var4 && !var3[var6].equals(this.hours[var2].workingEmployees[var5]); ++var6) {
            }

            if (var6 == var4) {
                var3[var4++] = this.hours[var2].workingEmployees[var5];
            }
        }

        String[] var7 = new String[var4];

        for(var6 = 0; var6 < var4; ++var6) {
            var7[var6] = var3[var6];
        }

        return var7;
    }

    public int nextIncomplete(int var1) {
        for(int var2 = var1; var2 < this.hours.length; ++var2) {
            if (this.hours[var2].requiredNumber > this.hours[var2].workingEmployees.length) {
                return var2;
            }
        }

        return -1;
    }

    public class Hour {
        int requiredNumber;
        String[] workingEmployees;

        public Hour() {
        }
    }
}
