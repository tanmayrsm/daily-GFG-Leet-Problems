// HOW I CAME WITH BELOW APPROACH ?

// Eg - 1
// BASE - SS | PPSS | SPS | PSPPPS

// below were required Partitions -

// SSP | PSS | SPS | PSPPPS
// SSP |PSS | SPSP | SPPPS

// SSPP | SS | SPS | PSPPPS
// SSPP |SS | SPSP | SPPPS

// SS | PPSS | SPSP | SPPPS

// thus I realised, that I need to count only no of Ps after a successfull pair of S

// Eg - 2
// --------------------------------------------
// BASE - SS | PPSS | SPS | PSPPPS | PPSS => 18

// 2P -> 1 * (2P + 1) = 3
// 1P -> 3 * (1P + 1) = 6
// 2P -> 6 * (2P + 1) = 18

// Eg - 3 - just appended - PPPSPS ast last
// --------------------------------------------
// BASE - SS | PPSS | SPS | PSPPPS | PPSS | PPPSPS => 72

// 3p -> 18 * (3p + 1) = 72     .... 18 from last example



class Solution {
    static int mod = 1000000007;
    public int numberOfWays(String s) {
        int n = s.length();
        int i = 0;
        long ans = 1;
        while(i < n) {
            int sCt = 0;
            while(i < n && sCt != 2) {
                if(s.charAt(i) == 'S')
                    sCt++;
                i++;
            }
            if(sCt != 2)    // no of S if just 1 after a partition, we can never divide it to requirement
                return 0;

            int plantCt = 0;
            while(i < n && s.charAt(i) == 'P') {
                plantCt++;
                i++;
            }
            if(i < n)   // as I want to ignore plant count after last segragation of 'S' pair
                        // ie. ignore SSPP -> last 2 P count must be ignored
            {
                ans *= (plantCt + 1);
                ans %= mod; 
            }   
        }
        return (int)(ans % 1000000007);
    }
}