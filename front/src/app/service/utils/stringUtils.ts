import { map, Observable } from "rxjs";

export class StringUtils {

    // To lowercase matches
    public static toLowerCase() {
        return (req: Observable<string>) => 
            req.pipe(map((value: string) => value.toLowerCase()));
    }

    // Remove whitespace
    public static trim() {
        return (req: Observable<string>) => 
            req.pipe(map((value: string) => value.trim()));
    }
}