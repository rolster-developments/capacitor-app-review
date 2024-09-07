import Foundation
import Capacitor
import StoreKit

@objc(AppReviewPlugin)
public class AppReviewPlugin: CAPPlugin {
    @objc func request(_ call: CAPPluginCall) {
        SKStoreReviewController.requestReviewInCurrentScene()

        call.resolve()
    }
    
    @objc func openStore(_ call: CAPPluginCall) {
        call.resolve()
    }
}

extension SKStoreReviewController {
    public static func requestReviewInCurrentScene() {
        if #available(iOS 14.0, *) {
            if let scene = UIApplication.shared.connectedScenes.first(where: { $0.activationState == .foregroundActive }) as? UIWindowScene {
                DispatchQueue.main.async {
                    requestReview(in: scene)
                }
            }
        } else if #available(iOS 10.3, *) {
            requestReview()
        }
    }
}
