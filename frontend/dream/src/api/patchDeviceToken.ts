import axios from "axios";

export async function patchDeviceToken(token:string) {
    const AlertUrl = '22' // 임시
    const data = await axios.patch(
        `${AlertUrl}/api/추후`,
        { deviceToken: token },
        {
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${AlertUrl}`,
            }
        }
    )

    return data
}